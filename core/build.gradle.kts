import dependencies.Dependencies

plugins{
    id("commons.android-library")
}
allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.vmadalin.core.annotations.OpenClass")
}

dependencies {
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CORE_KTX)
}