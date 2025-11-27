# Android Converter SDK

A **modular, Kotlin-based Android SDK** for flexible file conversion utilities.  
Designed to be **lightweight, easy-to-use, and future-proof**, this SDK currently supports ZIP file operations, with plans for image and PDF modules.

---

## 🌟 Features

- **Core Module (`core`)**
  - The main module of the SDK.
  - Automatically includes the `zip` module (and future modules like `image` or `pdf`).
  - Provides a single entry point for developers who want all conversion utilities at once.

- **ZIP Module (`zip`)**
  - Standalone module for ZIP file operations.
  - Create, extract, and list ZIP files.
  - Can be used independently if you only need ZIP functionality.

- **Future Modules**
  - `image` → Image compression, resizing, and format conversion.
  - `pdf` → PDF creation, merging, and splitting.
  - Easy to integrate via the `core` module when ready.

---

## 📦 Installation via JitPack

### 1️⃣ Add JitPack repository

In your **root `settings.gradle.kts`**:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
