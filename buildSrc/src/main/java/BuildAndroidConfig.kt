import com.android.builder.model.ApiVersion

object BuildAndroidConfig {

    const val APPLICATION_ID = "com.dapzthelegend.tictactoe"

    const val BUILD_TOOL_VERSION = "30.0.2"
    const val COMPILE_SDK_VERSION = 30
    const val MIN_SDK_VERSION = 22
    const val TARGET_SDK_VERSION = 30

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val SUPPORT_LIBRARY_VECTOR_DRAWABLES = true

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    val TEST_INSTRUMENTATION_RUNNER_ARGUMENTS = mapOf(
        "leakcanary.FailTestOnLeakRunListener" to "listener"
    )
}