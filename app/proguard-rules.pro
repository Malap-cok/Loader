# This is a configuration file for R8, the new code shrinker from Google.
# Please see https://www.guardsquare.com/en/products/proguard for more details.

# Keep all public classes
-keep public class * {*;}

# Keep JNI methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep custom application classes
-keep class com.cosmic.loader.** { *; }

# Preserve line numbers
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
