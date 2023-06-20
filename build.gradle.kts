// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1")
        // Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
//plugins {
//    id("com.android.application") version "7.5.0" apply false
//    id("com.android.library") version "7.5.0" apply false
//    id("org.jetbrains.kotlin.android") version "1.5.31" apply false
//    id("androidx.navigation.safeargs.kotlin") version "2.4.0" apply false
//    id("com.google.dagger.hilt.android") version "2.46.1" apply false
//}
