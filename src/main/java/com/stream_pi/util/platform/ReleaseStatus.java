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


package com.stream_pi.util.platform;

import com.stream_pi.util.i18n.I18N;

import java.io.Serializable;

public enum ReleaseStatus implements Serializable
{
    EA, GA;

    private static final long serialVersionUID = 2039570019678396L;

    public String getUIName()
    {
        if (this == EA)
        {
            return I18N.getString("platform.ReleaseStatus.ea");
        }
        else
        {
            return I18N.getString("platform.ReleaseStatus.ga");
        }
    }
}
