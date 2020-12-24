package com.StreamPi.Util.StartAtBoot;

import com.StreamPi.Util.Exception.MinorException;
import com.StreamPi.Util.Platform.Platform;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class StartAtBoot {

    SoftwareType softwareType;
    Platform platform;

    public StartAtBoot(SoftwareType softwareType, Platform platform)
    {
        this.softwareType = softwareType;
        this.platform = platform;
    }

    public void create(File runnerFile) throws MinorException
    {
        if(platform == Platform.WINDOWS)
            createStarterForWindows(runnerFile);
        else if(platform == Platform.LINUX || platform == Platform.LINUX_RPI)
            createStarterForLinux(runnerFile);
        else if(platform == Platform.MAC)
            createStarterForMac(runnerFile);
        else if(platform == Platform.UNKNOWN)
            unknownPlatformException();
    }

    public boolean delete() throws MinorException {
        if(platform == Platform.WINDOWS)
            return deleteStarterForWindows();
        else if (platform == Platform.LINUX || platform == Platform.LINUX_RPI)
            return deleteStarterForLinux();
        else if(platform == Platform.MAC)
            deleteStarterForMac();
        else if(platform == Platform.UNKNOWN)
            unknownPlatformException();

        return false;
    }

    private void createStarterForLinux(File runnerFile) throws MinorException
    {
        File initFile = new File("/etc/init.d/streampi_starter_"+ softwareType);

        try
        {
            FileWriter fw = new FileWriter(initFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("#! /bin/sh\n" +
                    "cd "+runnerFile.getAbsoluteFile().getParent()+"\n" +
                    "sudo ./"+runnerFile.getName()+"\n" +
                    "exit 0\n");
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new MinorException(e.getMessage()+"\nTry running as root and try again");
        }
    }

    private boolean deleteStarterForLinux()
    {
        return new File("/etc/init.d/streampi_starter_"+ softwareType).delete();
    }

    private void createStarterForWindows(File runnerFile) throws MinorException
    {
        File initFile = new File(System.getenv("APPDATA")+"/Microsoft/Windows/Start Menu/Programs/Startup/streampi_starter_"+ softwareType +".bat");

        try
        {
            FileWriter fw = new FileWriter(initFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("cd "+runnerFile.getParentFile().getCanonicalPath()+"\n" +
                    runnerFile.getName());
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
