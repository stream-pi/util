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
            unknownPlatformException();
        else if(platform == Platform.UNKNOWN)
            unknownPlatformException();

        return false;
    }

    private void createStarterForLinux(File runnerFile) throws MinorException
    {
        File initFile = new File("/etc/init.d/streampi_starter"+ softwareType);

        try
        {
            FileWriter fw = new FileWriter(initFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("cd "+runnerFile.getParentFile().getCanonicalPath()+"\n" +
                    "./"+runnerFile.getName());
            bw.close();
        }
        catch (Exception e)
        {
            throw new MinorException(e.getMessage());
        }
    }

    private boolean deleteStarterForLinux()
    {
        return new File("/etc/init.d/streampi_starter"+ softwareType).delete();
    }

    private void createStarterForWindows(File runnerFile) throws MinorException
    {
        File initFile = new File(System.getenv("APPDATA")+"/Microsoft/Windows/Start Menu/Programs/Startup/streampi_starter"+ softwareType +".bat");

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
        return new File(System.getenv("APPDATA")+"/Microsoft/Windows/Start Menu/Programs/Startup/streampi_starter"+ softwareType +".bat").delete();
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
