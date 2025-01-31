import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ru.amalkoott.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")


        val keystoreFile = project.rootProject.file( "api.properties" )
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        val baseApi = properties.getProperty( "BASE_API" ) ?: ""
        val loadDataEndpoint = properties.getProperty( "LOAD_DATA" ) ?: ""

        buildConfigField(
            type = "String" ,
            name = "BASE_API" ,
            value = baseApi
        )
        buildConfigField(
            type = "String" ,
            name = "LOAD_DATA" ,
            value = loadDataEndpoint
        )
    }
    buildFeatures{
        buildConfig = true
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
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.okhttp)

    // di
    implementation(libs.dagger.hilt.android)  // или актуальная версия
    ksp(libs.dagger.hilt.compiler)  // Для KSP
}