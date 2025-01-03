plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose)
//    kotlin("plugin.compose")
//    id("org.jetbrains.compose")
//    alias(libs.plugins.kotlinx.serialization)
//    kotlin("plugin.serialization") version "1.6.0"
}

kotlin {
    jvm()
//    js(IR) {
//        browser()
//    }
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
//        val ktor: String by project
        commonMain.dependencies {
            //put your multiplatform dependencies here
//            implementation("io.ktor:ktor-client-core:$ktor")
//            implementation("io.ktor:ktor-client-cio:$ktor")
//            implementation("io.ktor:ktor-client-content-negotiation:$ktor")
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
//            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
//            implementation(libs.kotlinx.serialization.core)
//            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json") {
//                version {
//                    strictly("1.6.0")
//                }
//                exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-serialization-json-io")
//                exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-serialization-core-jvm")
//            }

//            implementation(libs.)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
    }
}

android {
    namespace = "com.example.demokmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation(libs.androidx.ui.text.android)
    implementation(libs.androidx.foundation.android)
}
