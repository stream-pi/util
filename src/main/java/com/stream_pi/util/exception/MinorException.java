package com.stream_pi.util.exception;

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
