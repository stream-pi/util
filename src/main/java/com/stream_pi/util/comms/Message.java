package com.stream_pi.util.comms;

import java.io.Serializable;

public class Message implements Serializable
{
    private final String header;

    private String[] stringArrValue;
    private String stringValue;
    private byte[] byteArrValue;

    public Message(String header)
    {
        this.header = header;
    }

    public void setByteArrValue(byte[] byteArrValue)
    {
        this.byteArrValue = byteArrValue;
    }

    public void setStringArrValue(String... stringArrValue)
    {
        this.stringArrValue = stringArrValue;
    }

    public void setStringValue(String stringValue)
    {
        this.stringValue = stringValue;
    }

    public String getHeader()
    {
        return header;
    }

    public byte[] getByteArrValue()
    {
        return byteArrValue;
    }

    public String[] getStringArrValue()
    {
        return stringArrValue;
    }

    public String getStringValue()
    {
        return stringValue;
    }

}
