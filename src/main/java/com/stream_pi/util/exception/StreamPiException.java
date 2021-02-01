package com.stream_pi.util.exception;

public class StreamPiException extends Exception {
    private String title;
    private String message;


    public StreamPiException(String message)
    {
        super(message);

        this.title = "";
        this.message = message;
    }

    public StreamPiException(String title, String message)
    {
        super(message);
        this.title = title;
        this.message = message;
    }

    public String getShortMessage()
    {
        return message;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setShortMessage(String message)
    {
        this.message = message;
    }
}
