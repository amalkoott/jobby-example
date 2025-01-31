plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    //alias(libs.plugins.kotlin.kapt)
    //kotlin("kapt") version "2.1.10"
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ru.amalkoott.database"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // room
    implementation(libs.androidx.room.runtime)

    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // type converter
    implementation (libs.converter.gson)

    // di
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
}