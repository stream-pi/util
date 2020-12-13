/*
ReleaseStatus.java

Contributors : Debayan Sutradhar (@dubbadhar)

Enum to store the current status of the Server Release
 */
package com.StreamPi.Util.Platform;

public enum ReleaseStatus {
    EA, GA;

    public static String getUIName(ReleaseStatus releaseStatus)
    {
        switch (releaseStatus)
        {
            case EA: return "Early Access";
            case GA: return "General Availability";
            default:
                return null;
        }
    }
}
