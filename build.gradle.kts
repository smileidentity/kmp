plugins {
    // Applied to all sub-modules
    alias(libs.plugins.ktlint)

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.cocoapods) apply false
}

tasks.create("clean", Delete::class.java) {
    delete(rootProject.layout.buildDirectory)
}

ktlint {
    android = true
}
