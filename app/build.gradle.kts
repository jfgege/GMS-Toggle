plugins {
    id("com.android.application")
}

android {
    namespace = "com.jifeng.gmstoggle"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jifeng.gmstoggle"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.fragment:fragment:1.8.5")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.core:core:1.15.0")
}
