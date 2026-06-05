package com.cosmic.loader;

import android.os.Build;
import android.os.Debug;

public class DeviceInfoHelper {

    public static String getDeviceInfo() {
        StringBuilder deviceInfo = new StringBuilder();
        deviceInfo.append("Device: ").append(Build.DEVICE).append("\n");
        deviceInfo.append("Model: ").append(Build.MODEL).append("\n");
        deviceInfo.append("Manufacturer: ").append(Build.MANUFACTURER).append("\n");
        deviceInfo.append("Android: ").append(Build.VERSION.RELEASE).append("\n");
        deviceInfo.append("SDK: ").append(Build.VERSION.SDK_INT).append("\n");
        deviceInfo.append("Arch: ").append(getArchitecture()).append("\n");
        deviceInfo.append("RAM: ").append(getRAMInfo()).append(" MB\n");
        
        return deviceInfo.toString();
    }

    private static String getArchitecture() {
        // Deteksi arsitektur CPU
        String abi = Build.CPU_ABI;
        if (abi.contains("arm64")) {
            return "ARM64";
        } else if (abi.contains("armeabi")) {
            return "ARMv7";
        } else if (abi.contains("x86_64")) {
            return "x86_64";
        } else if (abi.contains("x86")) {
            return "x86";
        }
        return abi;
    }

    private static long getRAMInfo() {
        return Runtime.getRuntime().maxMemory() / (1024 * 1024);
    }
}
