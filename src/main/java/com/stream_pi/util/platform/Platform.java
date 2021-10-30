/*
 * Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macropad
 * Copyright (C) 2019-2021  Debayan Sutradhar (rnayabed),  Samuel Quiñones (SamuelQuinones)
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

package com.stream_pi.util.platform;

import com.stream_pi.util.i18n.I18N;

import java.io.Serializable;

public enum Platform implements Serializable
{
    WINDOWS,
    LINUX,
    MAC,
    ANDROID,
    IOS,
    UNKNOWN;

    private static final long serialVersionUID = 2038742019667976L;

    public String getUIName()
    {
        if (this == WINDOWS)
        {
            return I18N.getString("platform.Platform.windows");
        }
        else if(this == LINUX)
        {
            return I18N.getString("platform.Platform.linux");
        }
        else if(this == MAC)
        {
            return I18N.getString("platform.Platform.mac");
        }
        else if(this == ANDROID)
        {
            return I18N.getString("platform.Platform.android");
        }
        else if(this == IOS)
        {
            return I18N.getString("platform.Platform.ios");
        }
        else
        {
            return I18N.getString("platform.Platform.unknown");
        }
    }
}
