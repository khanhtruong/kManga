import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}


val devKeyStorePropertiesFile: File = rootProject.file("dev.properties")
val devKeyStoreProperties = Properties()
devKeyStoreProperties.load(FileInputStream(devKeyStorePropertiesFile))

val uatKeyStorePropertiesFile: File = rootProject.file("uat.properties")
val uatKeyStoreProperties = Properties()
uatKeyStoreProperties.load(FileInputStream(uatKeyStorePropertiesFile))

val releaseKeyStorePropertiesFile: File = rootProject.file("release.properties")
val releaseKeyStoreProperties = Properties()
releaseKeyStoreProperties.load(FileInputStream(releaseKeyStorePropertiesFile))

android {
    namespace = Config.PACKAGE_NAME
    compileSdk = Config.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = Config.PACKAGE_NAME
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
        versionCode = Config.MAJOR * 100000 + Config.MINOR * 10000 + Config.PATCH * 1000 + 1
        versionName = "${Config.MAJOR}.${Config.MINOR}.${Config.PATCH}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    signingConfigs {
        create("dev") {
            keyAlias = devKeyStoreProperties["KEY_ALIAS"].toString()
            keyPassword = devKeyStoreProperties["KEY_PASSWORD"].toString()
            storePassword = devKeyStoreProperties["KEY_STORE_PASSWORD"].toString()
            storeFile = file(devKeyStoreProperties["KEY_FILE_DIR"].toString())
        }

        create("uat") {
            keyAlias = uatKeyStoreProperties["KEY_ALIAS"].toString()
            keyPassword = uatKeyStoreProperties["KEY_PASSWORD"].toString()
            storePassword = uatKeyStoreProperties["KEY_STORE_PASSWORD"].toString()
            storeFile = file(uatKeyStoreProperties["KEY_FILE_DIR"].toString())
        }

        create("release") {
            keyAlias = releaseKeyStoreProperties["KEY_ALIAS"].toString()
            keyPassword = releaseKeyStoreProperties["KEY_PASSWORD"].toString()
            storePassword = releaseKeyStoreProperties["KEY_STORE_PASSWORD"].toString()
            storeFile = file(releaseKeyStoreProperties["KEY_FILE_DIR"].toString())
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            resValue("string", "app_name", "${Config.MAJOR}.${Config.MINOR}.${Config.PATCH}-Debug")
            signingConfig = signingConfigs.getByName("debug")
            versionNameSuffix = "_debug"
        }
        create("uat") {
            isDebuggable = false
            isMinifyEnabled = false
            resValue("string", "app_name", "${Config.MAJOR}.${Config.MINOR}.${Config.PATCH}-UAT")
            versionNameSuffix = "-uat"
            signingConfig = signingConfigs.getByName("uat")
            buildConfigField("String", "VARIANT", "\"uat\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            resValue("string", "app_name", "kManga")
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("String", "VARIANT", "\"release\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions += "env"
    productFlavors {
        create("dev") {
            dimension = "env"
            buildConfigField("String", "SERVER_BASE_URL", "\"https://myanimelist.p.rapidapi.com\"")
        }
        create("prod") {
            dimension = "env"
            buildConfigField("String", "SERVER_BASE_URL", "\"https://myanimelist.p.rapidapi.com\"")
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

    implementation("androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}")
    implementation("androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}")

    testImplementation("junit:junit:${Versions.TEST_JUNIT_VERSION}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.TEST_EXT_JUNIT_VERSION}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.TEST_ESPRESSO_VERSION}")

    // Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}")
    androidTestImplementation("androidx.navigation:navigation-testing:${Versions.NAVIGATION_VERSION}")

    // Retrofit
    implementation("com.google.code.gson:gson:${Versions.GSON_VERSION}")
    implementation("com.squareup.okhttp3:okhttp:${Versions.OKHTTP_3_RETROFIT_VERSION}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_3_RETROFIT_VERSION}")
    implementation("com.squareup.retrofit2:retrofit:${Versions.RETROFIT_2_VERSION}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_2_VERSION}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT_2_VERSION}")
    implementation("com.squareup.retrofit2:converter-scalars:${Versions.RETROFIT_2_VERSION}")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VERSION}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_VERSION}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${Versions.LIFECYCLE_VERSION}")

    // Material design
    implementation("com.google.android.material:material:${Versions.GOOGLE_MATERIAL_VERSION}")

    // Kotlin Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINE_VERSION}")

    // Glide - Image loader
    implementation("com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}")

    // Swipe to refresh layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT_VERSION}")

    // Dagger - hilt
    implementation("com.google.dagger:hilt-android:${Versions.HILT_VERSION}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}")

    implementation("androidx.hilt:hilt-navigation-fragment:${Versions.HILT_NAVIGATION_FRAGMENT_VERSION}")

    // Pager indicator
    implementation("com.tbuonomo:dotsindicator:${Versions.PAGER_INDICATOR_VERSION}")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:${Versions.PAGING_VERSION}")
    testImplementation("androidx.paging:paging-common-ktx:${Versions.PAGING_VERSION}")

    implementation("com.jakewharton.rxbinding2:rxbinding:${Versions.RX_BINDING_VERSION}")

    //Room
    implementation("androidx.room:room-runtime:${Versions.ROOM_VERSION}")
    implementation("androidx.room:room-ktx:${Versions.ROOM_VERSION}")
    annotationProcessor("androidx.room:room-compiler:${Versions.ROOM_VERSION}")
    kapt("androidx.room:room-compiler:${Versions.ROOM_VERSION}")
}

kapt {
    correctErrorTypes = true
}
