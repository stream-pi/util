package com.streampi.util.loggerhelper;

import java.util.logging.ConsoleHandler;

public class StreamPiLogFallbackHandler extends ConsoleHandler{
    public StreamPiLogFallbackHandler() throws Exception
    {
        super();

        setFormatter(new StreamPiLogFormatter());
    }

    public void closeHandler()
    {
        close();
    }
}
