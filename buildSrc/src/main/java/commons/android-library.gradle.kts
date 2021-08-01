package commons

import BuildAndroidConfig
import BuildProductDimensions
import ProductFlavorDevelop
import ProductFlavorProduction
import ProductFlavorQA
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.addTestsDependencies

plugins {
    id("com.android.library")
    id("commons.android-base-convention")
    id("commons.kotlin-base-convention")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-allopen")
    id("com.vanniktech.android.junit.jacoco")
    id("com.vanniktech.dependency.graph.generator")
}

android {
    @Suppress("UnstableApiUsage")
    buildFeatures{
        dataBinding = true
    }
}

junitJacoco {
    includeNoLocationClasses = true
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.TIMBER)

    kapt(AnnotationProcessorsDependencies.DAGGER)


    addTestsDependencies()
}
