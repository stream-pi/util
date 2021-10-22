package com.stream_pi.util.comms;

import com.stream_pi.util.i18n.I18N;

import java.io.Serializable;

public enum DisconnectReason implements Serializable
{
    COMM_STANDARD_MISMATCH;

    private static final long serialVersionUID = 2039570019671976L;

    public String getMessage()
    {
        if (this == COMM_STANDARD_MISMATCH)
        {
            return I18N.getString("comms.DisconnectReason.commStandardMismatch");
        }

        return null;
    }
}
