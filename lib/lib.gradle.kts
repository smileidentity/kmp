import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.android.library)
    alias(libs.plugins.maven.publish)
}

val groupId = "com.smileidentity"
val artifactId = "kmp-sdk"
project.version =
    findProperty("VERSION_NAME") as? String ?: file("VERSION").readText().trim().toString()

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class) compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.smile.android)
        }
    }
}

android {
    namespace = groupId
    resourcePrefix = "si_"
    compileSdk = 35

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()
    coordinates(groupId, artifactId)
    pom {
        name = "Smile ID KMP SDK"
        description = "The Official Smile ID KMP SDK"
        url = "https://docs.usesmileid.com/integration-options/mobile/kmp-v1-beta"
        licenses {
            license {
                name = "Smile ID Terms of Use"
                url = "https://usesmileid.com/terms-and-conditions"
                distribution = "repo"
            }
            license {
                name = "The MIT License"
                url = "https://opensource.org/licenses/MIT"
                distribution = "repo"
            }
        }
        scm {
            url = "https://github.com/smileidentity/kmp"
            connection = "scm:git:git://github.com/smileidentity/kmp.git"
            developerConnection = "scm:git:ssh://github.com/smileidentity/kmp.git"
        }
        developers {
            developer {
                id = "jumaallan"
                name = "Juma Allan"
                email = "juma@usesmileid.com"
                url = "https://github.com/jumaallan"
                organization = "Smile ID"
                organizationUrl = "https://usesmileid.com"
            }
            developer {
                id = "mugolazarus"
                name = "Mugo Lazarus"
                email = "mugolazarusk@gmail.com"
                url = "https://github.com/lazarusmugo"
                organization = "Tajji Ltd"
                organizationUrl = "https://tajji.io"
            }
        }
    }
}
