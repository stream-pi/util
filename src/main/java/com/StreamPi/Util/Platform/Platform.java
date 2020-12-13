package com.StreamPi.Util.Platform;

public enum Platform {
    WINDOWS, LINUX, MAC, ANDROID, IOS, LINUX_RPI, UNKNOWN;

    public static String getUIName(Platform platform)
    {
        switch (platform)
        {
            case WINDOWS: return "Windows";
            case LINUX: return "Linux";
            case LINUX_RPI : return "Linux Raspberry Pi";
            case MAC: return "MacOS";
            case ANDROID: return "Android";
            case IOS: return "iOS";
            case UNKNOWN:
            default:
                return "Unknown";
        }
    }
}
