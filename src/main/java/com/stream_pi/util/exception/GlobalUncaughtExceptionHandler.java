/*
 * Stream-Pi - Free, Open-Source, Modular, Cross-Platform and Programmable Macro Pad
 * Copyright (C) 2019-2022 Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
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

package com.stream_pi.util.exception;

import com.stream_pi.util.alert.StreamPiAlert;
import com.stream_pi.util.alert.StreamPiAlertType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
    final private Logger logger = Logger.getLogger(GlobalUncaughtExceptionHandler.class.getName());

    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
        logger.log(Level.SEVERE, "Uncaught exception occurred!", e);
        new StreamPiAlert("Uncaught Exception Occurred!", e.getLocalizedMessage(), StreamPiAlertType.ERROR).show();
    }

    public static void init()
    {
        Logger.getLogger(GlobalUncaughtExceptionHandler.class.getName()).severe("TEsttt");
        Thread.setDefaultUncaughtExceptionHandler(new GlobalUncaughtExceptionHandler());
    }
}
