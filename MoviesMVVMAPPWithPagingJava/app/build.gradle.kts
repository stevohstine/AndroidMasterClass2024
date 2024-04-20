plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
}


android {
    namespace = "com.masterclass.moviesmvvmappwithpagingjava"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.masterclass.moviesmvvmappwithpagingjava"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures{
        viewBinding = true
        dataBinding = true
    }

}

dependencies {

    val lifecycleVersion = "2.7.0"
    val pagingVersion = "3.2.1"
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.10.0")
    //RXJava3 with retrofit
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.10.0")

    //SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    //Paging Library
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    // optional - RxJava3 support
    implementation("androidx.paging:paging-rxjava3:$pagingVersion")

    //Hilt Dagger
    implementation ("com.google.dagger:hilt-android:2.51.1")
    annotationProcessor ("com.google.dagger:hilt-compiler:2.51.1")
}