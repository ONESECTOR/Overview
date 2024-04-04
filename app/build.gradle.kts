plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.sector.overview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sector.overview"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // ViewBinding Delegate
    implementation(libs.viewbinding.delegate)

    // Adapter Delegate
    implementation(libs.adapter.delegate.dsl)
    implementation(libs.adapter.delegate.dsl.viewbinding)
    implementation(libs.firebase.firestore.ktx)

    // Orbit MVI
    implementation(libs.orbit.mvi.core)
    implementation(libs.orbit.mvi.viewmodel)

    // DI
    implementation(libs.koin)
}