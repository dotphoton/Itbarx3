package com.itbarxproject.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Umut Boz on 12.09.2015.
 */
public class DiskUtility {
    public static final Pattern DIR_SEPORATOR = Pattern.compile("/");
    private static final String VIDEO_PATH_DIR = "videos/";
    private static   String INTERNAL_PATH_DIR = "/data/data/";

    public static String getInternalPath (Context context)
    {
      return  INTERNAL_PATH_DIR = context.getPackageName() + DIR_SEPORATOR;
    }

    public static String getRemovableStoragePath() {

        File f = null;
//      Log.d(TAG, "Build.DEVICE: " + Build.DEVICE);
//      Log.d(TAG, "Build.MANUFACTURER: " + Build.MANUFACTURER);
//      Log.d(TAG, "Build.MODEL: " + Build.MODEL);

        if (Build.VERSION.RELEASE.startsWith("4")) {
            f = new File("/mnt/emmc");
        }
        if (Build.MODEL.toLowerCase().startsWith("mele")) {
            return "/mnt/extern_sd0";
        }
        if (Build.DEVICE.equals("nuclear-zoop") || Build.DEVICE.equals("nuclear-f900")) {
            f = new File("/mnt/extsd");
        }

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String extendedPath = "";
        if (Build.DEVICE.toLowerCase().contains("samsung") || Build.MANUFACTURER.toLowerCase().contains("samsung")) {
            extendedPath = "/external_sd/";
            try {
                f = new File(path + extendedPath);
                if (f.exists() && f.isDirectory()) {
                    return f.getAbsolutePath();
                } else if (Build.MODEL.toLowerCase().contains("gt-i9300")) {
                    extendedPath = "/mnt/extSdCard/";
                    try {
                        f = new File(extendedPath);
                        if (f.exists() && f.isDirectory()) {
                            return f.getAbsolutePath();
                        }
                    } catch (Exception e) {
                        // continue execution
                    }
                } else {
                    extendedPath = "/sd";
                }
            } catch (Exception e) {
                // contine execution
            }
        } else if (Build.DEVICE.toLowerCase().contains("e0") || Build.MANUFACTURER.toLowerCase().contains("LGE")) {
            extendedPath = "/_ExternalSD/";
        } else if (Build.MANUFACTURER.toLowerCase().contains("motorola") || Build.DEVICE.toLowerCase().contains("olympus")) {
            f = new File("/mnt/sdcard-ext");
        }
        try {
            if (!extendedPath.equals("")) {
                f = new File(path + extendedPath);
            }
            if (f.exists() && f.isDirectory()) {
                //              Log.d(TAG, "path: " + f.getAbsolutePath());
                return f.getAbsolutePath();
            } else {
            }
        } catch (Exception e) {
            //          e.printStackTrace();
            // f is probably null. no need to print stacktrace.
            return path;
        }

        return path;
//      return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
    /**
     * Raturns all available SD-Cards in the system (include emulated)
     *
     * Warning: Hack! Based on Android source code of version 4.3 (API 18)
     * Because there is no standart way to get it. TODO: Test on future Android
     * versions 4.4+
     *
     * @return paths to all available SD-Cards in the system (include emulated)
     */

    public static String[] getStorageDirectories() {
        // Final set of paths
        final Set<String> rv = new HashSet<String>();
        // Primary physical SD-CARD (not emulated)
        final String rawExternalStorage = System.getenv("EXTERNAL_STORAGE");
        // All Secondary SD-CARDs (all exclude primary) separated by ":"
        final String rawSecondaryStoragesStr = System
                .getenv("SECONDARY_STORAGE");
        // Primary emulated SD-CARD
        final String rawEmulatedStorageTarget = System
                .getenv("EMULATED_STORAGE_TARGET");
        if (TextUtils.isEmpty(rawEmulatedStorageTarget)) {
            // Device has physical external storage; use plain paths.
            if (TextUtils.isEmpty(rawExternalStorage)) {
                // EXTERNAL_STORAGE undefined; falling back to default.
                rv.add("/storage/sdcard0");
            } else {
                rv.add(rawExternalStorage);
            }
        } else {
            // Device has emulated storage; external storage paths should have
            // userId burned into them.
            final String rawUserId;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                rawUserId = "";
            } else {
                final String path = Environment.getExternalStorageDirectory()
                        .getAbsolutePath();
                final String[] folders = DIR_SEPORATOR.split(path);
                final String lastFolder = folders[folders.length - 1];
                boolean isDigit = false;
                try {
                    Integer.valueOf(lastFolder);
                    isDigit = true;
                } catch (NumberFormatException ignored) {
                }
                rawUserId = isDigit ? lastFolder : "";
            }
            // /storage/emulated/0[1,2,...]
            if (TextUtils.isEmpty(rawUserId)) {
                rv.add(rawEmulatedStorageTarget);
            } else {
                rv.add(rawEmulatedStorageTarget + File.separator + rawUserId);
            }
        }
        // Add all secondary storages
        if (!TextUtils.isEmpty(rawSecondaryStoragesStr)) {
            // All Secondary SD-CARDs splited into array
            final String[] rawSecondaryStorages = rawSecondaryStoragesStr
                    .split(File.pathSeparator);
            Collections.addAll(rv, rawSecondaryStorages);
        }
        return rv.toArray(new String[rv.size()]);
    }


    public boolean hasSdCard() {
        boolean isSDPresent = false;
        try {
            isSDPresent = Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
        } catch (Exception ex) {
            Log.d("FileCacheUtil hasSdCard", ex.getMessage());
        }
        return isSDPresent;

    }

    public  static  String getExternalPath(Context context)
    {
        String path ="";
        String[] directories = getStorageDirectories();
        for (String dir : directories) {
            Log.d("getExternalPath getStorageDirectories", dir);
        }
        String searchDir = "/storage/emulated/0";
        boolean hasSeachDir = false;
        for (String dir : directories) {
            if (dir.equalsIgnoreCase(searchDir)) {
                hasSeachDir = true;
                break;
            }
        }
        if (hasSeachDir) {
            path  = searchDir;
            path += "/" + context.getPackageName()+ "/data/";
        } else {
            if (directories != null && directories.length > 0) {
                path = directories[0];
                Log.d("FileCacheUtil SDCARD_DATA_DIRECTORY",
                        path);
                path += "/" + context.getPackageName() + "/data/";
            }
        }

        return  path;
    }
}
