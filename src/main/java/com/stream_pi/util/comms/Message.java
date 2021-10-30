/*
 * Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
 * Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.stream_pi.util.comms;


import java.io.Serializable;
import java.util.HashMap;

/**
 * Message class to be sent between Server and Client using sockets
 */
public class Message implements Serializable
{
    private final String header;
    private final HashMap<String, Object> values = new HashMap<>();
    private static final long serialVersionUID = 2004200019671976L;

    /**
     * Default Constructor for Message
     * @param header Message Header Text
     */
    public Message(String header)
    {
        this.header = header;
    }

    public String getHeader()
    {
        return header;
    }

    public void setValue(String key, Object value)
    {
        values.put(key, value);
    }

    public Object getValue(String key)
    {
        return values.getOrDefault(key, null);
    }
}
