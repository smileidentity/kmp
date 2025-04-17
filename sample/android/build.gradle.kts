plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.smileidentity.kmp.sample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.smileidentity.kmp.sample"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // modules
    implementation(projects.lib)
    // androidx
    implementation(libs.bundles.android)
    androidTestImplementation(libs.bundles.android.test)
    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.base)
    implementation(libs.bundles.compose.mobile)
    debugImplementation(libs.bundles.compose.debug)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.bundles.compose.android.test)
}
