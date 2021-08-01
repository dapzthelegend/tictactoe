plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
    maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
}

object PluginsVersions{
    const val GRADLE_ANDROID = "7.0.0"
    const val GRADLE_VERSIONS = "0.33.0"
    const val KOTLIN = "1.4.31"
    const val NAVIGATION = "2.3.0"
    const val JACOCO = "0.16.0"
    const val KTLINT = "0.39.0"
    const val DETEKT = "1.17.0"
    const val GRAPH_GENERATOR = "0.7.0-SNAPSHOT"
}


dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.vanniktech:gradle-android-junit-jacoco-plugin:${PluginsVersions.JACOCO}")
    implementation("com.vanniktech:gradle-dependency-graph-generator-plugin:${PluginsVersions.GRAPH_GENERATOR}")
    implementation("com.pinterest:ktlint:${PluginsVersions.KTLINT}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.DETEKT}")
}