package com.stream_pi.util.platform;

public enum Platform {
    WINDOWS("Windows"),
    LINUX("Linux"), 
    MAC("MacOS"),
    ANDROID("Android"),
    IOS("iOS"),
    LINUX_RPI("Raspberry Pi"),
    UNKNOWN("Unknown");

    final private String UIName;

    Platform(String UIName)
    {
        this.UIName = UIName;
    }

    public String getUIName()
    {
        return UIName;
    }
}
