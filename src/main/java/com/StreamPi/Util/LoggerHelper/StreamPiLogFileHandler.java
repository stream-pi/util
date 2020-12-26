package com.StreamPi.Util.LoggerHelper;

import java.io.IOException;
import java.util.logging.FileHandler;

public class StreamPiLogFileHandler extends FileHandler {

    public StreamPiLogFileHandler() throws IOException, SecurityException 
    {
        super("streampi.log", false);

        setFormatter(new StreamPiLogFormatter());
    }
    
}
