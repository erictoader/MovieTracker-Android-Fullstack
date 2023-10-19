@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.serialization)
    id("kotlin-parcelize")
}

fun getInt(param: Provider<String>) = Integer.parseInt(param.get())

android {
    namespace = "com.erictoader.ui"
    compileSdk = getInt(libs.versions.compileSdk)

    defaultConfig {
        minSdk = getInt(libs.versions.minSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtension.get()
    }
}

dependencies {
    implementation(project(":domain"))

    // Core Android
    implementation(libs.bundles.androidx)

    // Jetpack Compose
    implementation(libs.bundles.compose)
    implementation(libs.compose.ratingbar)
    implementation(libs.compose.icons)

    val composeBom = platform("androidx.compose:compose-bom:2023.05.01")
    implementation(composeBom)

    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Serialisation
//    implementation(libs.jsonSerialization)

    implementation("com.squareup.moshi:moshi:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")


    // Coil image loading
    implementation(libs.coil)

    // Lottie animation
    implementation (libs.lottie)

    // Koin Di
    implementation(libs.bundles.koin)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.junit.test)
    testImplementation(libs.androidx.arch.core)
}
