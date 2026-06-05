#include <jni.h>
#include <string>
#include <openssl/sha.h>
#include <time.h>

// Simple license validation (can be enhanced with more complex logic)
extern "C" JNIEXPORT jboolean JNICALL
Java_com_cosmic_loader_LicenseManager_validateLicense(
        JNIEnv *env, jclass clazz, jstring license_key) {

    const char *license = env->GetStringUTFChars(license_key, nullptr);
    
    // Simple validation: check length and format
    std::string key(license);
    
    // Minimum length check (at least 32 characters)
    if (key.length() < 32) {
        env->ReleaseStringUTFChars(license_key, license);
        return false;
    }
    
    // Check if all characters are hexadecimal
    for (char c : key) {
        if (!isxdigit(c)) {
            env->ReleaseStringUTFChars(license_key, license);
            return false;
        }
    }
    
    env->ReleaseStringUTFChars(license_key, license);
    return true;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_cosmic_loader_LicenseManager_generateLicense(
        JNIEnv *env, jclass clazz, jstring device_id) {

    const char *device = env->GetStringUTFChars(device_id, nullptr);
    
    // Generate a simple license key based on device ID
    std::string license_key = std::string(device) + std::to_string(time(nullptr));
    
    env->ReleaseStringUTFChars(device_id, device);
    return env->NewStringUTF(license_key.c_str());
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_cosmic_loader_LicenseManager_isLicenseExpired(
        JNIEnv *env, jclass clazz, jstring license_key) {
    
    // Placeholder for license expiration check
    // In production, this would validate against a server or stored timestamp
    
    return false; // License is not expired
}
