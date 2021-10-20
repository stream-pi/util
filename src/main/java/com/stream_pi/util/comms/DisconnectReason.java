package com.stream_pi.util.comms;

import com.stream_pi.util.i18n.I18N;

public enum DisconnectReason
{
    COMM_STANDARD_MISMATCH;

    public String getMessage()
    {
        if (this == COMM_STANDARD_MISMATCH)
        {
            return I18N.getString("comms.DisconnectReason.commStandardMismatch");
        }

        return null;
    }
}
