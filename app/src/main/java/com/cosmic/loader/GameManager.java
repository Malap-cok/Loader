package com.cosmic.loader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import java.io.File;

public class GameManager {

    private static final String GAME_CLONE_PATH = "/sdcard/Android/data/com.cosmic.loader/";

    /**
     * Cek apakah OBB file tersedia di internal storage
     */
    public static boolean hasOBB(String gameName) {
        String obbPath = Environment.getExternalStorageDirectory() + "/Android/obb/" + getPackageName(gameName);
        File obbFile = new File(obbPath);
        return obbFile.exists();
    }

    /**
     * Install game ke dalam clone
     */
    public static void installGame(Context context, String gameName) {
        try {
            String packageName = getPackageName(gameName);
            // Copy APK dan OBB ke game clone path
            File clonePath = new File(GAME_CLONE_PATH + gameName);
            if (!clonePath.exists()) {
                clonePath.mkdirs();
            }
            // Proses cloning dilakukan di sini
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cek apakah game sudah terinstall di clone
     */
    public static boolean isGameInstalled(Context context, String gameName) {
        File gamePath = new File(GAME_CLONE_PATH + gameName);
        return gamePath.exists() && new File(gamePath, "base.apk").exists();
    }

    /**
     * Launch game dari clone
     */
    public static void launchGame(Context context, String gameName) {
        try {
            String packageName = getPackageName(gameName);
            // Launch game menggunakan PackageManager
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uninstall game dari clone
     */
    public static void uninstallGame(Context context, String gameName) {
        try {
            File gamePath = new File(GAME_CLONE_PATH + gameName);
            if (gamePath.exists()) {
                deleteDirectory(gamePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get package name berdasarkan nama game
     */
    private static String getPackageName(String gameName) {
        switch (gameName) {
            case "PUBG Global":
                return "com.tencent.ig";
            case "PUBG Korea":
                return "com.pubg.krmobile";
            case "PUBG Taiwan":
                return "com.pubg.tmobile";
            case "PUBG Vietnam":
                return "com.pubg.vn";
            case "BGMI":
                return "com.pubg.imobile";
            default:
                return "";
        }
    }

    /**
     * Hapus directory secara rekursif
     */
    private static void deleteDirectory(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    deleteDirectory(child);
                }
            }
        }
        file.delete();
    }
}
