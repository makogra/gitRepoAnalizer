plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
//    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
//    alias(libs.plugins.compose.compiler).apply(false)
}

repositories {
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}
//
//configurations.all {
//    resolutionStrategy {
//        force("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.0")
//    }
//}
