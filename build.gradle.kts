buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.navigationSafeargsKotlin) apply false
    alias(libs.plugins.orgJetbrainsKotlinSerialization) apply false
}