rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "tictactoe"

include (
    ":app",
    ":core",
    ":features:home",
    ":features:multi_player",
    ":commons:ui"
)

pluginManagement {

    repositories {
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://plugins.gradle.org/m2/")
        maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
        maven("https://dl.bintray.com/kotlin/kotlin-dev/")//for kapt plugin 1.4.30

    }

    resolutionStrategy {
        eachPlugin {
            val pluginId = requested.id.id

            if (pluginId.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.5.20")
            } else if (pluginId.startsWith("com.android.")) {
                useModule("com.android.tools.build:gradle:7.0.0")
            }
        }
    }
}


