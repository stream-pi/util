package com.StreamPi.Util.Exception;

public class MinorException extends StreamPiException {

    public MinorException(String message)
    {
        super(message);
    }

    public MinorException(String title, String message)
    {
        super(title, message);
    }
}
