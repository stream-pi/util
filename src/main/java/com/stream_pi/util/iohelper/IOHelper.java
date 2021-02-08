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

package com.stream_pi.util.iohelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class IOHelper {

    public static void unzip(InputStream inputStream, String destDir) throws Exception {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        InputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];

        fis = inputStream;
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();

                File newFile = new File(destDir + File.separator + fileName);

                if(ze.isDirectory())
                {
                    newFile.mkdirs();
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
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
    }
}
