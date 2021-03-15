/*
Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

Originally Written by : Debayan Sutradhar (rnayabed)
*/

package com.stream_pi.util.startatboot;

import com.stream_pi.util.platform.Platform;
import com.stream_pi.util.exception.MinorException;
import com.stream_pi.util.platform.PlatformType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class StartAtBoot {

    PlatformType softwareType;
    Platform platform;

    public StartAtBoot(PlatformType softwareType, Platform platform)
    {
        this.softwareType = softwareType;
        this.platform = platform;
    }

    public void create(File runnerFile) throws MinorException
    {
        if(platform == Platform.WINDOWS)
            createStarterForWindows(runnerFile);
        else if(platform == Platform.LINUX)
            createStarterForLinux(runnerFile, true);
        else if(platform == Platform.MAC)
            createStarterForMac(runnerFile);
        else if(platform == Platform.UNKNOWN)
            unknownPlatformException();
    }

    public void create(File runnerFile, boolean isXMode)  throws MinorException
    {
        if(platform == Platform.WINDOWS)
            createStarterForWindows(runnerFile);
        else if(platform == Platform.LINUX)
            createStarterForLinux(runnerFile, isXMode);
        else if(platform == Platform.MAC)
            createStarterForMac(runnerFile);
        else if(platform == Platform.UNKNOWN)
            unknownPlatformException();
    }

    public boolean delete() throws MinorException {
        if(platform == Platform.WINDOWS)
            return deleteStarterForWindows();
        else if (platform == Platform.LINUX)
            return deleteStarterForLinux();
        else if(platform == Platform.MAC)
            deleteStarterForMac();
        else if(platform == Platform.UNKNOWN)
            unknownPlatformException();

        return false;
    }

    private void createStarterForLinux(File runnerFile, boolean isXMode) throws MinorException
    {
        try
        {
            String sysDDirectoryPath = System.getProperty("user.home")+"/.local/share/systemd/user/";

            File sysDDirectoryFile = new File(sysDDirectoryPath);

            if(!sysDDirectoryFile.exists())
                sysDDirectoryFile.mkdirs();

            File sysDServiceFile = new File(sysDDirectoryPath+"stream-pi-"+ softwareType+".service");

            FileWriter fw = new FileWriter(sysDServiceFile);
            BufferedWriter bw = new BufferedWriter(fw);

            if(isXMode)
            {
                bw.write("[Unit]\n" +
                        "Description=Stream-Pi "+softwareType+"\n" +
                        "[Service]\n" +
                        "Type=simple\n" +
                        "Environment=\"DISPLAY=:0\"\n" +
                        "WorkingDirectory=\""+runnerFile.getAbsoluteFile().getParent()+"\"\n" +
                        "ExecStart="+runnerFile.getAbsoluteFile().getParent()+"/"+runnerFile.getName()+"\n" +
                        "[Install]\n" +
                        "WantedBy=default.target\n");
            }
            else
            {
                bw.write("[Unit]\n" +
                        "Description=Stream-Pi "+softwareType+"\n" +
                        "[Service]\n" +
                        "Type=oneshot\n" +
                        "WorkingDirectory=\""+runnerFile.getAbsoluteFile().getParent()+"\"\n" +
                        "ExecStart="+runnerFile.getAbsoluteFile().getParent()+"/"+runnerFile.getName()+"\n" +
                        "[Install]\n" +
                        "WantedBy=default.target\n");
            }

            bw.close();

            Runtime.getRuntime().exec("systemctl --user daemon-reload");
            Runtime.getRuntime().exec("systemctl --user enable stream-pi-"+softwareType+".service");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new MinorException("Unable to set start at boot",e.getMessage());
        }
    }

    private boolean deleteStarterForLinux() throws MinorException
    {
        try
        {
            boolean f1 = new File(System.getProperty("user.home")+"/.local/share/systemd/user/stream-pi-"+
                    softwareType+".service").delete();

            Runtime.getRuntime().exec("systemctl --user daemon-reload");

            return f1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new MinorException("Unable to unset start at boot",e.getMessage());
        }
    }

    private void createStarterForWindows(File runnerFile) throws MinorException
    {
        File initFile = new File(System.getenv("APPDATA")+"/Microsoft/Windows/Start Menu/Programs/Startup/streampi_starter_"+ softwareType +".bat");

        try
        {
            FileWriter fw = new FileWriter(initFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("cd "+runnerFile.getAbsoluteFile().getParent()+"\r\n" +
                    "start \"\" "+runnerFile.getName());
            bw.close();
        }
        catch (Exception e)
        {
            throw new MinorException(e.getMessage());
        }
    }

    private boolean deleteStarterForWindows()
    {
        return new File(System.getenv("APPDATA")+"/Microsoft/Windows/Start Menu/Programs/Startup/streampi_starter_"+ softwareType +".bat").delete();
    }


    private void createStarterForMac(File runnerFile) throws MinorException
    {
        throw new MinorException("Mac Starter feature is not implemented yet.");
    }

    private void deleteStarterForMac() throws MinorException
    {
        throw new MinorException("Mac Starter feature is not implemented yet.");
    }
    
    private void unknownPlatformException() throws MinorException
    {
        throw new MinorException("Cannot implemented starter feature. Unknown platform.");
    }
}
