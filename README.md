# COSMIC LOADER - PUBGM Android 64BIT Virtual Container

## 📱 Deskripsi Project
COSMIC LOADER adalah aplikasi loader virtual container untuk PUBGM Android 64BIT tanpa memerlukan akses root. Aplikasi ini memungkinkan pemain untuk menjalankan berbagai varian PUBG secara bersamaan di satu perangkat.

## 🎮 Fitur Utama

### 1. **Authentication System**
- Splash Screen dengan animasi loading
- Login menggunakan License Key
- Validasi license melalui JNI (C++)
- Support untuk multiple license management

### 2. **Main Interface**
- Timer otomatis dengan countdown (1 jam default)
- Menampilkan informasi device secara real-time
- Menu start/stop untuk kontrol timer
- ESP Overlay support (Star dan Stop menu)

### 3. **Multi-Game Support**
Mendukung 5 varian PUBG:
- 🌍 PUBG Global (com.tencent.ig)
- 🇰🇷 PUBG Korea (com.pubg.krmobile)
- 🇹🇼 PUBG Taiwan (com.pubg.tmobile)
- 🇻🇳 PUBG Vietnam (com.pubg.vn)
- 🇮🇳 BGMI (com.pubg.imobile)

### 4. **Game Management**
Setiap game memiliki 3 opsi:
- **Install Game**: Clone game dari internal storage (deteksi otomatis OBB)
- **Launch Game**: Menjalankan game dari clone
- **Uninstall Game**: Menghapus game clone tanpa menggangu game original

### 5. **Desain Modern Gaming**
- Dark theme dengan neon color scheme
- UI responsif dan user-friendly
- Animasi smooth dan visual menarik

## 🏗️ Struktur Project

```
Loader/
├── app/
│   ├── libs/
│   │   └── MUNDO-PREMIUM.aar (Virtual Container Support)
│   ├── src/main/
│   │   ├── java/com/cosmic/loader/
│   │   │   ├── ActivitySplash.java
│   │   │   ├── ActivityLogin.java
│   │   │   ├── ActivityMain.java
│   │   │   ├── LicenseManager.java
│   │   │   ├── GameManager.java
│   │   │   ├── GameOptionsDialog.java
│   │   │   └── DeviceInfoHelper.java
│   │   ├── jni/
│   │   │   └── cosmic_loader.cpp (License Validation)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_splash.xml
│   │   │   │   ├── activity_login.xml
│   │   │   │   ├── activity_main.xml
│   │   │   │   └── dialog_game_options.xml
│   │   │   ├── values/
│   │   │   │   ├── colors.xml
│   │   │   │   ├── strings.xml
│   │   │   │   └── themes.xml
│   │   │   └── mipmap/ (Icons)
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── keys/
│   └── (Signing keys for release build)
├── gradle/
├── build.gradle
├── gradle.properties
├── settings.gradle
├── signing.gradle
├── local.properties
└── README.md
```

## 🔧 Teknologi yang Digunakan

- **Language**: Java (Android), C++ (JNI)
- **SDK**: Android SDK 34
- **Min SDK**: 21 (Android 5.0)
- **Target SDK**: 34 (Android 14)
- **Build Tool**: Gradle 8.2.2
- **NDK**: 28.2.13676358

## 📋 Requirements

### System Requirements
- Android 5.0+ (API 21)
- RAM: Minimal 2GB (Recommended 4GB+)
- Storage: 500MB+ free space
- Architecture: 64-bit (ARM64)

### Development Requirements
- Android Studio Arctic Fox+
- Android NDK 28.2.13676358
- Gradle 8.2.2
- Java 17+

## 🚀 Build & Installation

### Build APK
```bash
./gradlew assembleDebug
```

### Build Release
```bash
./gradlew assembleRelease
```

### Build NDK
```bash
ndk-build -j$(nproc)
```

## 📝 Workflow Aplikasi

### 1. ActivitySplash
- Tampil selama 2 detik
- Loading animation
- Transisi ke ActivityLogin

### 2. ActivityLogin
- Input License Key
- Validasi melalui JNI
- Jika valid → ActivityMain
- Jika invalid → Error Toast

### 3. ActivityMain
- Timer otomatis 1 jam
- Display device info
- Menu 5 game PUBG
- Game management (Install/Launch/Uninstall)

## 🔐 License & Security

### License Validation
- Validasi dilakukan melalui JNI (C++)
- Format: Hexadecimal string minimal 32 karakter
- Support expiration date

### Virtual Container
- Menggunakan MUNDO-PREMIUM.aar untuk virtual container support
- Cloning game ke folder khusus tanpa root
- Isolasi sempurna dari game original

## 🎨 UI/UX Features

### Color Scheme
- Background: #1a1a1a (Dark Gray)
- Primary: #00ff00 (Neon Green)
- Secondary: #ffff00 (Neon Yellow)
- Accent: #ff00ff (Neon Magenta)

### Typography
- Bold untuk heading
- Standard untuk body text
- Size: 12sp - 32sp sesuai konteks

## 🐛 Troubleshooting

### Issue: "OBB tidak ada"
**Solution**: Install game PUBG original di device terlebih dahulu

### Issue: Game tidak bisa launch
**Solution**: Pastikan game sudah di-install melalui tombol "Install Game"

### Issue: License key tidak valid
**Solution**: Hubungi admin untuk mendapatkan license key yang benar

## 📞 Support & Contact

Untuk pertanyaan atau laporan bug, silakan buka issue di GitHub.

## 📄 License

Proyek ini dilisensikan di bawah lisensi proprietary. Penggunaan tanpa izin dilarang.

## 🙏 Credits

- MUNDO-PREMIUM.aar untuk virtual container technology
- Android Material Design Components
- OpenSSL untuk encryption

---

**Version**: 4.4.0  
**Last Updated**: 2026  
**Developer**: Malap-cok
