plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
//    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
//    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()
//    js(IR) {
//        browser {
//            // Configure the browser target
//            commonWebpackConfig {
//                cssSupport {
//                    enabled = true
//                }
//            }
//        }
//        binaries.executable()
//    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

//    tasks.withType<KotlinCompile>().configureEach {
//        kotlinOptions {
//            jvmTarget = "1.8"
//            freeCompilerArgs. += [
//                "-P",
//                "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
//            ]
//        }
//    }

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
//        val jsMain by getting {
//            dependencies {
//                implementation(kotlin("stdlib-js"))
//            }
//        }
//        val jsTest by getting {
//            dependencies {
//                implementation(kotlin("test-js"))
//            }
//        }
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negatiation)
            implementation(libs.ktor.serialization)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.serialization.json)

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
//        val commonMain by getting {
//            dependencies {
//                implementation(compose.runtime)
//                implementation(compose.foundation)
//                implementation(compose.material3)
//            }
//        }

        val androidMain by getting {
            dependencies {
                implementation(libs.navigation.compose)
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

//    buildFeatures {
//        compose = true
//    }
//    compileOptions {
//
//        kotlinCompilerExtensionVersion "1.5.5-dev-k2.0.0-Beta1-06b8ae672a4"
//    }

}

//composeCompiler {
//    reportsDestination = layout.buildDirectory.dir("compose_compiler")
//    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
//}



dependencies {
    implementation(libs.androidx.ui.text.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
//    implementation(files("/media/maciek/shared/dependenties/compose-compiler-1_5_13/compiler-hosted-1.5.13-dev-k2.0.0-RC1-50f08dfa4b4.jar"))
}
