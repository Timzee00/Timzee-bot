# Building the APK

This project is an Android app. To produce a downloadable APK, build it with Android Studio or the Gradle wrapper on a machine with the Android SDK installed.

## Option A: Android Studio
1. Open `android-assistant/` in Android Studio.
2. Allow it to sync Gradle.
3. Build > Build APK(s).

## Option B: Gradle CLI
1. Install the Android SDK and configure `ANDROID_HOME`.
2. From `android-assistant/`, run:

```bash
./gradlew assembleDebug
```

The APK will be located at:

```
android-assistant/app/build/outputs/apk/debug/app-debug.apk
```

## Notes
- This repository does not include SDK/NDK binaries. You must install the Android SDK locally.
- The app currently ships a skeleton runtime; connect a real offline model runtime before release.
