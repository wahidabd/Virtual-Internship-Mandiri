plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp") version "1.8.20-1.0.11"
}

android {
    namespace ="com.wahidabd.mandirinews"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.wahidabd.mandirinews"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
        buildConfigField("String", "API_KEY", "\"5b451eae6c2e4deebf2840f83664a528\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packaging {
        resources {
            excludes += ("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("META-INF/rxjava.properties")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // one library
    implementation("com.github.wahidabd:one-library:1.1.8")

    // accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

    // koin
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation("io.insert-koin:koin-androidx-workmanager:3.3.3")
    implementation("io.insert-koin:koin-androidx-navigation:3.3.3")
    implementation("io.insert-koin:koin-androidx-compose:3.4.0")

    // raam costa navigation
    implementation("io.github.raamcosta.compose-destinations:core:1.9.42-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.42-beta")

    // livedata compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.0-beta02")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // lifecycle
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.2-alpha")

    // Pagination
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    // logging
    implementation("com.jakewharton.timber:timber:5.0.1")
}