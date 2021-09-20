import dependencies.Dependencies
import dependencies.DebugDependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.addTestsDependencies


plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.BASE_ANDROID_APPLICATION)
    id(BuildPlugins.BASE_KOTLIN_APPLICATION)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
    id(BuildPlugins.KOTLIN_ALLOPEN)
 //   id(BuildPlugins.JACOCO)
    id(BuildPlugins.GRAPH_GENERATOR)
    id(BuildPlugins.GOOGLE_SERVICES)
    id(BuildPlugins.FIREBASE_CRASHLYTICS)
}

android {
    buildToolsVersion = BuildAndroidConfig.BUILD_TOOL_VERSION


    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunnerArguments.putAll(BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS)
    }

    signingConfigs {
        getByName("debug"){
            storeFile = File("/Users/dara/StudioProjects/tictactoe/app/key")
            storePassword = "chemistry1"
            keyPassword = "chemistry1"
            keyAlias = "key0"
        }
    }


    buildTypes {

        getByName("release") {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isDebuggable = false
            signingConfig = signingConfigs.findByName("debug")
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
        }
        getByName("debug"){
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }

    dynamicFeatures.addAll(mutableSetOf(
        BuildModules.Features.HOME,
        BuildModules.Features.GAME
    )
    )


    buildFeatures{
        dataBinding = true
    }
}

//junitJacoco {
//    includeNoLocationClasses = true
//}


dependencies {
    implementation(project(BuildModules.CORE))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.PLAY_CORE)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(platform(Dependencies.FIREBASE_BOM))
    implementation(Dependencies.FIREBASE_ANALYTICS)
    implementation(Dependencies.FIREBASE_CRASHLYTICS)

    debugImplementation(DebugDependencies.LEAKCANARY)

    kapt(AnnotationProcessorsDependencies.DAGGER)

    addTestsDependencies()
}
