@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

fun getInt(param: Provider<String>) = Integer.parseInt(param.get())

android {
    namespace = "com.erictoader.data"
    compileSdk = getInt(libs.versions.compileSdk)

    defaultConfig {
        minSdk = getInt(libs.versions.minSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use { properties.load(it) }
        } else return@defaultConfig

        // MovieTracker Back-end API
        buildConfigField("String", "BACK_END_MACHINE_ADDRESS", "\"${properties.getProperty("backEndMachineAddress")}\"")
        buildConfigField("String", "BACK_END_USER_API_PATH", "\"${properties.getProperty("backEndUserApiPath")}\"")
        buildConfigField("String", "BACK_END_WATCHLIST_API_PATH", "\"${properties.getProperty("backEndWatchlistApiPath")}\"")

        // TMDB API
        buildConfigField("String", "TMDB_API_QUERY", "\"${properties.getProperty("TMDB_API_QUERY")}\"")
        buildConfigField("String", "TMDB_API_KEY", "\"${properties.getProperty("TMDB_API_KEY")}\"")
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
    packagingOptions {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        )
    }
}

dependencies {
    implementation(project(":domain"))

    // Core Android
    implementation(libs.bundles.androidx)

    // Network Calls
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshiConverter)
    implementation(libs.moshi)

    // DataStore
    implementation(libs.datastore)

    // Koin Di
    implementation(libs.koin.android)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.junit.test)
    testImplementation(libs.androidx.arch.core)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.coroutines.test)
    androidTestImplementation(libs.androidx.espresso.core)
}
