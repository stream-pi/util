/*
Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

Originally Written by : Debayan Sutradhar (rnayabed)
*/

package com.stream_pi.util.alert;

/**
 * Stream-Pi Alert Type Enumerator
 */
public enum StreamPiAlertType {
    /**
     * Information Type Alert
     */
    INFORMATION("fas-info", "alert_information_icon"),

    /**
     * Warning Type Alert
     */
    WARNING("fas-exclamation-triangle", "alert_warning_icon"),

    /**
     * Error Type Alert
     */
    ERROR("fas-times", "alert_error_icon");

    private final String fontAwesomeIconCode;
    private final String alertIconStyleClassName;

    /**
     * @param fontAwesomeIconCode Ikonli Font Icon code
     * @param alertIconStyleClassName Icon CSS Style Class
     */
    StreamPiAlertType(String fontAwesomeIconCode, String alertIconStyleClassName)
    {
        this.fontAwesomeIconCode = fontAwesomeIconCode;
        this.alertIconStyleClassName = alertIconStyleClassName;
    }

    /**
     * @return Ikonli Font Icon code
     */
    public String getIconCode()
    {
        return fontAwesomeIconCode;
    }

    /**
     * @return Icon CSS Style Class
     */
    public String getIconStyleClassName()
    {
        return alertIconStyleClassName;
    }
}
