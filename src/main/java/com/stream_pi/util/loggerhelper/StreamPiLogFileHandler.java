package com.stream_pi.util.loggerhelper;

import java.util.logging.FileHandler;

public class StreamPiLogFileHandler extends FileHandler {

    public StreamPiLogFileHandler(String logFilePath) throws Exception
    {
        super(logFilePath, false);

        setFormatter(new StreamPiLogFormatter());
    }

    public void closeHandler()
    {
        close();
    }
    
}
