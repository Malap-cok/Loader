package com.cosmic.loader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;

public class PermissionHelper {

    /**
     * Check apakah semua permission yang diperlukan sudah granted
     */
    public static boolean hasAllPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) 
                != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Permissions yang diperlukan untuk game cloning
     */
    public static String[] getRequiredPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return new String[]{
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.MANAGE_EXTERNAL_STORAGE",
                "android.permission.QUERY_ALL_PACKAGES"
            };
        } else {
            return new String[]{
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.QUERY_ALL_PACKAGES"
            };
        }
    }
}
