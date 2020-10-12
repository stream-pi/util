package com.StreamPi.Util.Platform;

public enum Platform {
    WINDOWS, LINUX, MAC, ANDROID, IOS, OTHER;

    public static String getUIName(Platform platform)
    {
        switch (platform)
        {
            case WINDOWS: return "Windows";
            case LINUX: return "Linux";
            case MAC: return "MacOS";
            case ANDROID: return "Android";
            case IOS: return "iOS";
            default: return "Unknown";
        }
    }
}
