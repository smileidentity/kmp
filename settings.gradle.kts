enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "SmileID"
include("lib", "sample:cmp:app")
rootProject.children.forEach { it.buildFileName = "${it.name}.gradle.kts" }

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
