/*
ReleaseStatus.java

Written By : Debayan Sutradhar (@rnayabed)

Enum to store the current status of the Server Release
*/

package com.streampi.util.platform;

public enum ReleaseStatus {
    EA("Early Access"), GA("General Availability");

    private final String UIName;

    ReleaseStatus(String UIName)
    {
        this.UIName = UIName;
    }

    public String getUIName()
    {
        return UIName;
    }
}
