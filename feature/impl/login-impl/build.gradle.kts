plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ru.amalkoott.loginimpl"
    compileSdk = 35

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    //implementation(libs.androidx.core.ktx)
    //implementation(libs.androidx.appcompat)
    //implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // di
    implementation(libs.dagger.hilt.android)  // или актуальная версия
    ksp(libs.dagger.hilt.compiler)  // Для KSP
    //implementation(libs.androidx.lifecycle.viewmodel.compose)
    //implementation(libs.androidx.hilt.navigation.compose)

    //preview
    // Для работы с превью
    //debugImplementation(libs.androidx.ui.toolingn)
    //debugImplementation(libs.ui.tooling.preview)
    //implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.20")

    implementation(project(":core:feature-api"))
    implementation(project(":feature:api:login-api"))


    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.navigation.compose)
}