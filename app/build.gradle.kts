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
}

android {
    buildToolsVersion = BuildAndroidConfig.BUILD_TOOL_VERSION


    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunnerArguments.putAll(BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS)
    }


    buildTypes {

        getByName("release") {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
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
        BuildModules.Features.HOME, BuildModules.Features.MULTI_PLAYER
    ))


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

    debugImplementation(DebugDependencies.LEAKCANARY)

    kapt(AnnotationProcessorsDependencies.DAGGER)

    addTestsDependencies()
}
