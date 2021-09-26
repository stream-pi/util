/*
Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

Originally Written by : Debayan Sutradhar (rnayabed)
*/

package com.stream_pi.util.comms;


import java.io.Serializable;

/**
 * Message class to be sent between Server and Client using sockets
 */
public class Message implements Serializable
{
    private final String header;
    private static final long serialVersionUID = 2004200019671976L;
    private String[] stringArrValue;
    private String stringValue;
    private byte[] byteArrValue;
    private int intValue;
    private int[] intArrValue;
    private double doubleValue;
    private double[] doubleArrValue;

    private Object object = null;

    /**
     * Default Constructor for Message
     * @param header Message Header Text
     */
    public Message(String header)
    {
        this.header = header;
    }

    /**
     * @param doubleArrValue Double Array
     */
    public void setDoubleArrValue(double... doubleArrValue)
    {
        this.doubleArrValue = doubleArrValue;
    }

    /**
     * @return Double Array
     */
    public double[] getDoubleArrValue()
    {
        return doubleArrValue;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }

    public Object getObject()
    {
        return object;
    }

    /**
     * @param doubleValue Double argument
     */
    public void setDoubleValue(double doubleValue)
    {
        this.doubleValue = doubleValue;
    }

    /**
     * @return Double argument
     */
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
