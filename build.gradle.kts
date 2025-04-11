plugins {
    // Applied to all sub-modules
    alias(libs.plugins.ktlint)

    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.buildkonfig) apply false
}

tasks.create("clean", Delete::class.java) {
    delete(rootProject.layout.buildDirectory)
}

ktlint {
    android = true
}
