enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

//rootProject.name = "SmileID"
//include("lib", "sample")
//rootProject.children.forEach { it.buildFileName = "${it.name}.gradle.kts" }

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SmileID"

include(":composeApp")
include(":shared")
include(":lib")