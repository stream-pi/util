package com.stream_pi.util.comms;

import java.io.Serializable;

public class Message implements Serializable
{
    private String header;
    private String meta;
    private MessageType messageType;
    private Object value;

    public Message(String header, String meta, MessageType messageType, Object value)
    {
        this.header = header;
        this.meta = meta;
        this.messageType = messageType;
        this.value = value;
    }

    public void setMeta(String meta)
    {
        this.meta = meta;
    }

    public void setHeader(String header)
    {
        this.header = header;
    }

    public void setMessageType(MessageType messageType)
    {
        this.messageType = messageType;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public String getMeta()
    {
        return meta;
    }

    public String getHeader()
    {
        return header;
    }

    public MessageType getMessageType()
    {
        return messageType;
    }

    public Object getValue()
    {
        return value;
    }
}
