rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "tictactoe"

include (
    ":app",
    ":core",
    ":features:home",
    ":features:multi_player",
    ":commons:ui"
)


