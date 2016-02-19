package com.itbarxproject.utils;

import android.graphics.Bitmap;
import android.webkit.MimeTypeMap;

import com.itbarxproject.common.FileAttribute;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * Created by Umut Boz on 12.09.2015.
 */
public class FileUtility {


    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension);
        }
        return type;
    }

    private static int chmod2(String filename, int mode) {
        try {
            Class<?> fspClass = Class
                    .forName("java.util.prefs.FileSystemPreferences");
            Method chmodMethod = fspClass.getDeclaredMethod("chmod",
                    String.class, Integer.TYPE);
            chmodMethod.setAccessible(true);
            return (Integer) chmodMethod.invoke(null, filename, mode);
        } catch (Throwable ex) {
            return -1;
        }
    }

    public static int copyFile(InputStream inputStream, File dst)
            throws IOException {
        int status = 0;
        try {
            InputStream in = inputStream;
            OutputStream out = new FileOutputStream(dst);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

            status = 1;

        } catch (Exception ex) {
            status = -1;

        }
        return status;

    }

    public static void deleteLastDirectoryAndFiles(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {

            // directory is empty, then delete it
            if (fileOrDirectory.list() != null
                    && fileOrDirectory.list().length == 0) {

                fileOrDirectory.delete();
                System.out.println("Directory is deleted : "
                        + fileOrDirectory.getAbsolutePath());

            } else {

                // list all the directory contents
                String files[] = fileOrDirectory.list();

                for (String temp : files) {
                    // construct the file structure
                    File fileDelete = new File(fileOrDirectory, temp);

                    // recursive delete
                    deleteLastDirectoryAndFiles(fileDelete);
                }

                // check the directory again, if empty then delete it
                if (fileOrDirectory.list().length == 0) {
                    fileOrDirectory.delete();
                    System.out.println("Directory is deleted : "
                            + fileOrDirectory.getAbsolutePath());
                }
            }

        } else {
            // if file, then delete it
            fileOrDirectory.delete();
            System.out.println("File is deleted : "
                    + fileOrDirectory.getAbsolutePath());
        }
    }

    public static void deleteDirectoryAndChildFiles(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteDirectoryAndChildFiles(child);

        fileOrDirectory.delete();

    }

    public  static  void  deleteAllFileUnderFolder(String path)
    {
        File dir = new File(path);
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
        }
    }

    public static int chmod(File path, int mode) throws Exception {
        int val = 0;
        try {
            Class fileUtils = Class.forName("android.os.FileUtils");
            Method setPermissions = fileUtils.getMethod("setPermissions",
                    String.class, int.class, int.class, int.class);

            val = (Integer) setPermissions.invoke(null, path.getAbsolutePath(),
                    mode, -1, -1);
        } catch (Exception ex) {
            val = -1;
        }

        return val;

    }

    public static void copyDirectoryOneLocationToAnotherLocation(
            File sourceLocation, File targetLocation) throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < sourceLocation.listFiles().length; i++) {

                copyDirectoryOneLocationToAnotherLocation(new File(
                        sourceLocation, children[i]), new File(targetLocation,
                        children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);

            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }

    }

    public static int saveImageFileToDisk(Bitmap bitmap, String filePath,
                                            String fileName) {
        boolean success = false;
        int status = -1;
        try {
            File path = new File(filePath);
            if (!path.exists())
                path.mkdirs();
            File photoFile = new File(path.getAbsolutePath(), fileName);
            if (!photoFile.exists()) {
                FileOutputStream outStream;
                try {

                    outStream = new FileOutputStream(photoFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
					/* 100 to keep full quality of the image */

                    outStream.flush();
                    outStream.close();
                    success = true;
                    status = 1;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                status = 0;
                success = false;
            }

        } catch (Exception ex) {
            success = false;
        }
        return status;
    }
    public static int saveVideoFileToDisk(BufferedInputStream inStream,
                                            String filePath, String fileName) {
        int count;
        boolean success = false;
        int status = -1;
        try {
            File path = new File(filePath);
            if (!path.exists())
                path.mkdirs();
            File videoFile = new File(path.getAbsolutePath(), fileName);
            if (!videoFile.exists()) {
                FileOutputStream outStream;
                try {

                    outStream = new FileOutputStream(videoFile);
                    byte data[] = new byte[1024];

                    long total = 0;

                    while ((count = inStream.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
                        // publishProgress(""+(int)((total*100)/lenghtOfFile));

                        // writing data to file
                        outStream.write(data, 0, count);
                    }
                    outStream.flush();
                    outStream.close();
                    inStream.close();
                    success = true;
                    status = 1;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                status = 0;
                success = false;
            }

        } catch (Exception ex) {
            success = false;
        }
        return status;
    }

    public static FileAttribute getFileAttribute(String filePath) {
        FileAttribute fileAttribute = new FileAttribute(filePath, '/', '.');

        return fileAttribute;
    }

}
