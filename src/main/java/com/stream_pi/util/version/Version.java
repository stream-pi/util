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

package com.stream_pi.util.version;


import com.stream_pi.util.exception.MinorException;
import com.stream_pi.util.i18n.I18N;

import java.io.Serializable;

public class Version implements Serializable
{
    private static final long serialVersionUID = 20395700196031176L;
    private int major, minor, revision;

    public Version()
    {
        major = 0;
        minor = 0;
        revision = 0;
    }

    public Version(int major, int minor, int revision)
    {
        this.major = major;
        this.minor = minor;
        this.revision = revision;
    }

    public Version(String version) throws MinorException
    {
        String[] v = version.trim().split("\\.");
        if(v.length == 3)
        {
            try
            {
                major = Integer.parseInt(v[0]);
                minor = Integer.parseInt(v[1]);
                revision = Integer.parseInt(v[2]);
            }
            catch (NumberFormatException e)
            {
                throw new MinorException(I18N.getString("version.Version.invalidVersion", version));
            }
        }
        else
        {
            throw new MinorException(I18N.getString("version.Version.invalidVersion", version));
        }
    }

    public int getMajor()
    {
        return major;
    }

    public int getMinor()
    {
        return minor;
    }

    public int getRevision()
    {
        return minor;
    }

    public String getText()
    {
        return this.major+"."+this.minor+"."+this.revision;
    }

    public void setMajor(int major)
    {
        this.major = major;
    }

    public void setMinor(int minor)
    {
        this.minor = minor;
    }

    public void setRevision(int revision)
    {
        this.revision = revision;
    }

    public boolean isBiggerThan(Version version)
    {
        if (major == version.major)
        {
            if (minor == version.minor)
            {
               return revision > version.revision;
            }
            else
            {
                return minor > version.minor;
            }
        }
        else
        {
            return major > version.major;
        }
    }

    public boolean isEqual(Version version)
    {
        return version.major == this.major && version.minor == this.minor && version.revision == this.revision;
    }

}
