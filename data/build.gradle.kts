plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.sector.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "KINOPOISK_ENDPOINT", "\"https://api.kinopoisk.dev/v1.4/\"")
            buildConfigField("String", "KINOPOISK_API_KEY", "\"VJMNYZQ-FS14NFM-MPK4Z5V-59F50VH")
        }
        debug {
            isMinifyEnabled = false

            buildConfigField("String", "KINOPOISK_ENDPOINT", "\"https://api.kinopoisk.dev/v1.4/\"")
            buildConfigField("String", "KINOPOISK_API_KEY", "\"VJMNYZQ-FS14NFM-MPK4Z5V-59F50VH\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization.converter)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    // DI
    implementation(libs.koin)

    // OkHttp3
    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.okhttp3.interceptor)

    // Chucker
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    // Firebase Firestore Database
    implementation(libs.firebase.firestore.ktx)
}