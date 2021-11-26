/*
 * Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
 * Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.stream_pi.util.startonboot;

import com.stream_pi.util.i18n.I18N;
import com.stream_pi.util.platform.Platform;
import com.stream_pi.util.exception.MinorException;
import com.stream_pi.util.platform.PlatformType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class StartOnBoot
{
    private PlatformType softwareType;
    private Platform platform;
    private boolean isAppendPathBeforeRunnerFileToOvercomeJPackageLimitation;
    private URL mainClassPath;

    public StartOnBoot(PlatformType softwareType, Platform platform, URL mainClassPath, boolean isAppendPathBeforeRunnerFileToOvercomeJPackageLimitation)
    {
        this.softwareType = softwareType;
        this.platform = platform;
        this.mainClassPath = mainClassPath;
        this.isAppendPathBeforeRunnerFileToOvercomeJPackageLimitation = isAppendPathBeforeRunnerFileToOvercomeJPackageLimitation;
    }

    public void create(String runnerFileStr, String[] runtimeArguments) throws MinorException
    {
        create(runnerFileStr, true, runtimeArguments);
    }

    public void create(String argRunnerFileStr, boolean isXMode, String[] runtimeArguments)  throws MinorException
    {
        if (platform == Platform.UNKNOWN)
        {
            throw new MinorException(
                    I18N.getString("startonboot.StartOnBoot.unableToEnableStartOnBoot"),
                    I18N.getString("startonboot.StartOnBoot.unknownPlatform")
            );
        }

        if(argRunnerFileStr == null)
        {
            throw new MinorException(
                    I18N.getString("startonboot.StartOnBoot.unableToEnableStartOnBoot"),
                    I18N.getString("startonboot.StartOnBoot.noRunnerFileSpecified")
            );
        }

        String runnerFileStr = argRunnerFileStr;

        try
        {
            if(isAppendPathBeforeRunnerFileToOvercomeJPackageLimitation)
            {
                if(platform == Platform.LINUX)
                {
                    runnerFileStr = new File(mainClassPath.toURI()).getParentFile().getParentFile().getParentFile().getAbsolutePath() +
                            "/bin/" + argRunnerFileStr;
                }
                else if(platform == Platform.MAC)
                {
                    runnerFileStr = new File(mainClassPath.toURI()).getParentFile().getParentFile().getAbsolutePath() +
                            "/MacOS/" + argRunnerFileStr;
                }
                else
                {
                    throw new MinorException(I18N.getString("startonboot.StartOnBoot.flagNotSupported", "appendPathBeforeRunnerFileToOvercomeJPackageLimitation"));
                }
            }
        }
        catch (URISyntaxException e)
        {
            throw new MinorException(e.getMessage());
        }

        File runnerFile = new File(runnerFileStr);
        if(!runnerFile.isFile())
        {
            throw new MinorException(
                    I18N.getString("startonboot.StartOnBoot.unableToEnableStartOnBoot"),
                    I18N.getString("startonboot.StartOnBoot.runnerFileNotFound", runnerFile.getAbsolutePath())
            );
        }

        if(platform == Platform.WINDOWS)
            createStarterForWindows(runnerFile, runtimeArguments);
        else if(platform == Platform.LINUX)
            createStarterForLinux(runnerFile, isXMode, runtimeArguments);
        else if(platform == Platform.MAC)
            createStarterForMac(runnerFile, runtimeArguments);
    }

    public boolean delete() throws MinorException
    {
        if (platform == Platform.UNKNOWN)
        {
            throw new MinorException(
                    I18N.getString("startonboot.StartOnBoot.unableToDisableStartOnBoot"),
                    I18N.getString("startonboot.StartOnBoot.unknownPlatform")
            );
        }

        if(platform == Platform.WINDOWS)
            return deleteStarterForWindows();
        else if (platform == Platform.LINUX)
            return deleteStarterForLinux();
        else if(platform == Platform.MAC)
            return deleteStarterForMac();

        return false;
    }

    private void createStarterForLinux(File runnerFile, boolean isXMode, String[] runtimeArguments) throws MinorException
    {
        try
        {
            String sysDDirectoryPath = System.getProperty("user.home")+"/.local/share/systemd/user/";

            File sysDDirectoryFile = new File(sysDDirectoryPath);

            if(!sysDDirectoryFile.exists())
            {
                if(!sysDDirectoryFile.mkdirs())
                {
                    throw new Exception("Unable to create directories : '"+sysDDirectoryPath+"'");
                }
            }

            File sysDServiceFile = new File(sysDDirectoryPath+"stream-pi-"+ softwareType+".service");

            FileWriter fw = new FileWriter(sysDServiceFile);
            BufferedWriter bw = new BufferedWriter(fw);

            String execStart =  "\""+runnerFile.getAbsoluteFile().getParent()+"/"+runnerFile.getName()+"\" "+String.join(" ", runtimeArguments)+"\n";

            if(isXMode)
            {
                bw.write("[Unit]\n" +
                        "Description=Stream-Pi "+softwareType+"\n" +
                        "[Service]\n" +
                        "Type=simple\n" +
                        "ExecStartPre=/bin/sleep 3\n"+
                        "Environment=\"DISPLAY=:0\"\n" +
                        "ExecStart=" + execStart +
                        "[Install]\n" +
                        "WantedBy=default.target\n");
            }
            else
            {
                bw.write("[Unit]\n" +
                        "Description=Stream-Pi "+softwareType+"\n" +
                        "[Service]\n" +
                        "Type=oneshot\n" +
                        "ExecStart=" + execStart +
                        "[Install]\n" +
                        "WantedBy=default.target\n");
            }

            bw.close();

            runCommand("systemctl --user daemon-reload");
            runCommand("systemctl --user enable stream-pi-"+softwareType+".service");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new MinorException(I18N.getString("startonboot.StartOnBoot.unableToEnableStartOnBoot"), e.getLocalizedMessage());
        }
    }

    private boolean deleteStarterForLinux() throws MinorException
    {
        try
        {
            boolean f1 = new File(System.getProperty("user.home")+"/.local/share/systemd/user/stream-pi-"+
                    softwareType+".service").delete();

            runCommand("systemctl --user daemon-reload");

            return f1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new MinorException(I18N.getString("startonboot.StartOnBoot.unableToDisableStartOnBoot",e.getLocalizedMessage()));
        }
    }

    private void createStarterForWindows(File runnerFile, String[] runtimeArguments) throws MinorException
    {
        try
        {
            File initFile = new File(System.getenv("APPDATA")+"/stream_pi_starter_batch_"+ softwareType +".bat");


            FileWriter fw = new FileWriter(initFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("cd \""+runnerFile.getAbsoluteFile().getParent()+"\"\r\n" +
                    "cmd /k \"\""+runnerFile.getName()+"\" \""+String.join("\" \"", runtimeArguments)+"\"");
            bw.close();



            File vbsStarterFile = new File(System.getenv("APPDATA")+"/Microsoft/Windows/Start Menu/Programs/Startup/stream_pi_starter_"+ softwareType +".vbs");

            fw = new FileWriter(vbsStarterFile);
            bw = new BufferedWriter(fw);
            bw.write("Set WshShell = CreateObject(\"WScript.Shell\") \r\n" +
                    "WshShell.Run chr(34) & \""+initFile.getAbsolutePath()+"\" & Chr(34), 0\r\n" +
                    "Set WshShell = Nothing");
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new MinorException(I18N.getString("startonboot.StartOnBoot.unableToEnableStartOnBoot") ,e.getLocalizedMessage());
        }
    }
    
    private boolean deleteStarterForWindows()
    {
        boolean f1 = new File(System.getenv("APPDATA")+"/Microsoft/Windows/Start Menu/Programs/Startup/stream_pi_starter_"+ softwareType +".vbs").delete();
        boolean f2 = new File(System.getenv("APPDATA")+"/stream_pi_starter_batch_"+ softwareType +".bat").delete();

        return f1 && f2;
    }

    private void createStarterForMac(File runnerFile, String[] runtimeArguments) throws MinorException
    {
        try
        {
            String label = "com.stream-pi."+softwareType;

            File sysDServiceFile = new File(System.getProperty("user.home")+"/Library/LaunchAgents/"+label+".plist");

            FileWriter fw = new FileWriter(sysDServiceFile);
            BufferedWriter bw = new BufferedWriter(fw);

            StringBuilder arrayStrings = new StringBuilder();

            for (String arg : runtimeArguments)
            {
                arrayStrings
                        .append("<string>")
                        .append(arg)
                        .append("</string>\n");
            }

            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
                    "<plist version=\"1.0\">\n" +
                    "  <dict>\n" +
                    "    <key>Label</key>\n" +
                    "    <string>"+label+"</string>\n" +
                    "    <key>Program</key>\n" +
                    "    <string>"+runnerFile.getAbsoluteFile().getParent()+"/"+runnerFile.getName()+"</string>\n" +
                    "    <key>ProgramArguments</key>\n" +
                    "    <array>\n" + arrayStrings + "</array>\n"+
                    "    <key>RunAtLoad</key>\n" +
                    "    <true/>\n" +
                    "  </dict>\n" +
                    "</plist>");

            bw.close();

            runCommand("launchctl load -w '"+sysDServiceFile.getAbsolutePath()+"'");
            runCommand("launchctl stop "+label);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new MinorException(I18N.getString("startonboot.StartOnBoot.unableToEnableStartOnBoot"), e.getLocalizedMessage());
        }
    }

    private boolean deleteStarterForMac() throws MinorException
    {
        try
        {
            File pListFile = new File(System.getProperty("user.home")+"/Library/LaunchAgents/com.stream-pi."+softwareType+".plist");

            runCommand("launchctl unload -w '"+pListFile.getAbsolutePath()+"'");

            return pListFile.delete();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new MinorException(I18N.getString("startonboot.StartOnBoot.unableToDisableStartOnBoot"), e.getLocalizedMessage());
        }
    }

    private void runCommand(String command) throws IOException 
    {
        Runtime.getRuntime().exec(command);
    }
}
