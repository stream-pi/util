package com.stream_pi.util.comms;

import java.io.Serializable;

public class Message implements Serializable
{
    private static final long SerialVersionUID = 2004200019671976L;
    private final String header;
    private String[] stringArrValue;
    private String stringValue;
    private byte[] byteArrValue;
    private int intValue;
    private int[] intArrValue;
    private double doubleValue;
    private double[] doubleArrValue;

    public Message(String header)
    {
        this.header = header;
    }

    public void setDoubleArrValue(double... doubleArrValue)
    {
        this.doubleArrValue = doubleArrValue;
    }

    public double[] getDoubleArrValue()
    {
        return doubleArrValue;
    }

    public void setDoubleValue(double doubleValue)
    {
        this.doubleValue = doubleValue;
    }

    public double getDoubleValue()
    {
        return doubleValue;
    }

    public void setIntArrValue(int... intArrValue)
    {
        this.intArrValue = intArrValue;
    }

    public int[] getIntArrValue()
    {
        return intArrValue;
    }

    public void setIntValue(int intValue)
    {
        this.intValue = intValue;
    }

    public int getIntValue()
    {
        return intValue;
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
