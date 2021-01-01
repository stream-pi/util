package com.StreamPi.Util.LoggerHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;

public class StreamPiLogFileHandler extends FileHandler {

    public StreamPiLogFileHandler(String prePath) throws IOException, SecurityException 
    {
        super(prePath+"../streampi.log", false);

        setFormatter(new StreamPiLogFormatter());
    }

    public void closeHandler()
    {
        close();
    }
    
}
