package com.cosmic.loader;

public class LicenseManager {

    static {
        System.loadLibrary("cosmic_loader");
    }

    /**
     * Validasi license key melalui JNI (C++)
     * @param licenseKey License key dari user
     * @return true jika valid, false jika tidak valid
     */
    public static native boolean validateLicense(String licenseKey);

    /**
     * Generate license key baru (untuk admin/testing)
     * @param deviceId Device ID unik
     * @return Generated license key
     */
    public static native String generateLicense(String deviceId);

    /**
     * Cek apakah license sudah expired
     * @param licenseKey License key yang akan dicek
     * @return true jika expired, false jika masih berlaku
     */
    public static native boolean isLicenseExpired(String licenseKey);
}
