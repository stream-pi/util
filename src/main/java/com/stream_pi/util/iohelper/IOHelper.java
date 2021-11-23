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

package com.stream_pi.util.iohelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class IOHelper
{
    public static boolean unzip(InputStream inputStream, String destDir) throws IOException
    {
        File dir = new File(destDir);

        if(!dir.exists())
        {
            if(!dir.mkdirs())
            {
                return false;
            }
        }

        InputStream fis;
        byte[] buffer = new byte[1024];

        fis = inputStream;
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while(ze != null)
        {
            String fileName = ze.getName();

            File newFile = new File(destDir + File.separator + fileName);

            if(ze.isDirectory())
            {
                if(!newFile.mkdirs())
                {
                    return false;
                }
            }
            else
            {
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        fis.close();

        return true;
    }

    public static boolean deleteFile(String path, boolean deleteOnExit)
    {
        return deleteFile(new File(path), deleteOnExit);
    }

    public static boolean deleteFile(File file, boolean deleteOnExit)
    {
        if(deleteOnExit)
        {
            file.deleteOnExit();
        }

        if(file.isDirectory())
        {
            File[] files = file.listFiles();
            if(files == null)
            {
                return false;
            }

            for(File eachFile : files)
            {
                deleteFile(eachFile, deleteOnExit);
            }
        }

        Logger.getLogger(IOHelper.class.getName()).info("Deleting file "+file.getAbsolutePath()+" ...");

        if(!deleteOnExit)
        {
            return file.delete();
        }

        return true;
    }
}
