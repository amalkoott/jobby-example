plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ru.amalkoott.findjob"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.amalkoott.findjob"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "30.0.3"
}

dependencies {

    implementation(project(":feature:navigationbar"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.core.ktx)
    implementation (libs.material)
    implementation (libs.ui)
    implementation (libs.androidx.material)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.activity.compose)

    implementation(libs.androidx.material3.android)

    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    // di
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.androidx.room.compiler.ktx) // room with ksp


    implementation(project(":core:common"))
    implementation(project(":core:feature-api"))
    implementation(project(":core:di"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":feature:login:api"))
    implementation(project(":feature:favorites:api"))
    implementation(project(":feature:search:api"))
    implementation(project(":feature:response:api"))
    implementation(project(":feature:message:api"))
    implementation(project(":feature:profile:api"))
}