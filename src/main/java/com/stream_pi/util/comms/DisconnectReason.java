package com.stream_pi.util.comms;

import com.stream_pi.util.i18n.I18N;

import java.io.Serializable;

public enum DisconnectReason implements Serializable
{
    COMMUNICATION_PROTOCOL_MISMATCH;

    private static final long serialVersionUID = 2039570019671976L;

    public String getMessage()
    {
        if (this == COMMUNICATION_PROTOCOL_MISMATCH)
        {
            return I18N.getString("comms.DisconnectReason.communicationProtocolMismatch");
        }

        return null;
    }
}
