package commons

import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import org.gradle.kotlin.dsl.kotlin
import extensions.addTestsDependencies

plugins {
    id("com.android.dynamic-feature")
    id("commons.android-base-convention")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-allopen")
    id("androidx.navigation.safeargs.kotlin")
//    id("com.vanniktech.android.junit.jacoco")
    id("com.vanniktech.dependency.graph.generator")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {

    @Suppress("UnstableApiUsage")
    buildFeatures {
        dataBinding = true
    }

    android.buildTypes.getByName("release").debuggable(BuildAndroidConfig.DEBUGGABLE)



}

dependencies {
    implementation(project(BuildModules.APP))
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Commons.UI))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.FIREBASE_BOM)
    implementation(Dependencies.FIREBASE_ANALYTICS)
    implementation(Dependencies.FIREBASE_CRASHLYTICS)

    kapt(AnnotationProcessorsDependencies.DAGGER)

    addTestsDependencies()
}

