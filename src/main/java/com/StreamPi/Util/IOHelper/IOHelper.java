package com.StreamPi.Util.IOHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class IOHelper {
    public static void unzip(InputStream inputStream, String destDir) throws Exception{
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
                    while ((len = zis.read(buffer)) > 0) {
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
