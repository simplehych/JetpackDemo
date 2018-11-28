plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}


// 飘红 IDE 不支持 不予理会
android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.simple.roomwordsample"
        minSdkVersion(19)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.kotlin_stdlib)
    implementation(Deps.androidx.appcompat)
    implementation(Deps.androidx.constraintlayout)
    implementation(Deps.androidx.core)
    implementation(Deps.androidx.recyclerview)
    implementation(Deps.google.material)

    // Room components
    kapt(Deps.androidx.roomapt)
    implementation(Deps.androidx.room)

    // Lifecycle components
    implementation(Deps.androidx.lifecycle)
}


object Deps {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.10"

    object androidx {
        const val core = "androidx.core:core-ktx:1.0.1"
        const val appcompat = "androidx.appcompat:appcompat:1.0.2"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha2"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-extensions:2.0.0"
        const  val room = "androidx.room:room-runtime:2.1.0-alpha02"
        const val roomapt = "androidx.room:room-compiler:2.1.0-alpha02"
    }

    object google{
        const  val material = "com.google.android.material:material:1.0.0"
    }
}
