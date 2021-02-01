package com.streampi.util.loggerhelper;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class StreamPiLogFormatter extends SimpleFormatter{
    @Override
    public String format(LogRecord record)
    {
        return "["+Thread.currentThread().getName()+"] :: "+ record.getSourceClassName()+"@"+record.getSourceMethodName()+" -> " + ":" + record.getLevel() + " = "+ record.getMessage() + "\n";
    }
}
