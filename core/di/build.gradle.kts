plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.ksp)
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ru.amalkoott.core.di"
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
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:common"))

    implementation(project(":core:feature-api"))

    implementation(project(":feature:login:api"))
    implementation(project(":feature:search:api"))
    implementation(project(":feature:favorites:api"))
    implementation(project(":feature:response:api"))
    implementation(project(":feature:message:api"))
    implementation(project(":feature:profile:api"))

    implementation(project(":feature:login:impl"))
    implementation(project(":feature:search:impl"))
    implementation(project(":feature:favorites:impl"))
    implementation(project(":feature:profile:impl"))
    implementation(project(":feature:message:impl"))
    implementation(project(":feature:response:impl"))
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // di
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
}