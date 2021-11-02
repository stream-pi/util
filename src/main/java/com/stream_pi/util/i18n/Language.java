/*
 * Stream-Pi - Free & Open-Source Modular Cross-Platform Programmable Macro Pad
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

package com.stream_pi.util.i18n;

import java.util.Locale;

public class Language
{
    private final String displayName;
    private final Locale locale;

    public Language( Locale locale, String displayName)
    {
        this.displayName = displayName;
        this.locale = locale;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public Locale getLocale()
    {
        return locale;
    }
}
