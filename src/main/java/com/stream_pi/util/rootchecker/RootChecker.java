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

package com.stream_pi.util.rootchecker;

import com.stream_pi.util.i18n.I18N;
import com.stream_pi.util.platform.Platform;

import java.io.File;
import java.io.IOException;

public class RootChecker
{
    public static boolean isRoot(Platform platform)
    {
        File installDir;

        if (platform == Platform.WINDOWS)
        {
            installDir = new File("C:\\Windows");
        }
        else
        {
            installDir = new File("/root");
        }


        if (!installDir.canWrite())
            return false;

        if (!installDir.isDirectory())
            return false;

        File fileTest = null;
        try
        {
            // we use the .dll suffix to properly test on Vista virtual directories
            // on Vista you are not allowed to write executable files on virtual directories like "Program Files"
            fileTest = File.createTempFile("writtableArea", ".dll", installDir);
        }
        catch (IOException e)
        {
            //If an exception occured while trying to create the file, it means that it is not writable
            return false;
        }
        finally
        {
            if (fileTest != null)
            {
                fileTest.delete();
            }
        }
        return true;
    }

    public static String getRootNotAllowedI18NString()
    {
        return I18N.getString("rootchecker.RootChecker.rootNotAllowed");
    }
}
