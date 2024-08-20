plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.dev.tools.ksp)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
}

android {
    namespace = "com.heyrex.mauth"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
            buildConfigField("String", "HOST_API", "\"https://ab655b67-72b6-4051-8be9-26e0b4491f74.mock.pstmn.io\"")
            buildConfigField("okhttp3.logging.HttpLoggingInterceptor.Level", "LEVEL_LOGS", "okhttp3.logging.HttpLoggingInterceptor.Level.NONE")
        }
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "HOST_API", "\"https://ab655b67-72b6-4051-8be9-26e0b4491f74.mock.pstmn.io\"")
            buildConfigField("okhttp3.logging.HttpLoggingInterceptor.Level", "LEVEL_LOGS", "okhttp3.logging.HttpLoggingInterceptor.Level.BODY")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
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

    implementation(libs.core.ktx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp)
    implementation("androidx.compose.material:material-icons-extended:1.0.0")

    // Test mockito
    testImplementation(libs.mockito)
    androidTestImplementation(libs.mockitoAndroid)
    testImplementation(libs.mockitoKotlin)

    // Test MockWebServer
    testImplementation(libs.mockk)
    testImplementation(libs.coroutineTest)

    implementation("androidx.compose.material:material-icons-extended:1.0.0")
}