rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "tictactoe"

include (
    ":app",
    ":core",
    ":features:home",
    ":commons:ui"
)

