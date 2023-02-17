object Version {

    internal object AndroidX {
        const val core = "1.7.0"
        const val appcompat = "1.6.1"
        const val compose = "1.0.5"
        const val constraintLayout = "2.1.4"
        const val activity = "1.6.1"
        const val collection = "1.2.0"
        const val multidex = "2.0.1"
        const val lifecycle = "2.5.1"
        const val swipeRefreshLayout = "1.1.0"
        const val viewpager2 = "1.0.0"
        const val room = "2.4.3"
        const val navigation = "2.5.3"
        const val paging = "3.1.1"
        const val materialUi = "1.0.1"
        const val legacy = "1.0.0"
        const val fragment = "1.5.5"
    }

    object Google {
        const val material = "1.8.0"
        const val gson = "2.8.9"
        const val hilt = "2.44.2"
    }

    internal object Squareup {
        const val retrofit2 = "2.9.0"
        const val okhttp3 = "4.10.0"
    }

    internal object Github {
        const val glide = "4.14.2"
    }

    internal object Jetbrains {
        const val kotlinxCoroutines = "1.6.1"
    }
}

object AndroidX {
    const val core = "androidx.core:core-ktx:${Version.AndroidX.core}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Version.AndroidX.legacy}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.AndroidX.appcompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.AndroidX.constraintLayout}"
    const val activity = "androidx.activity:activity-ktx:${Version.AndroidX.activity}"
    const val activityCompose = "androidx.activity:activity-compose:${Version.AndroidX.activity}"
    const val collection = "androidx.collection:collection-ktx:${Version.AndroidX.collection}"
    const val multidex = "androidx.multidex:multidex:${Version.AndroidX.multidex}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.AndroidX.swipeRefreshLayout}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Version.AndroidX.viewpager2}"
    const val composeUi = "androidx.compose.ui:ui:${Version.AndroidX.compose}"
    const val composeMaterial = "androidx.compose.material3:material3:${Version.AndroidX.materialUi}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Version.AndroidX.compose}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.AndroidX.compose}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.AndroidX.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.AndroidX.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.AndroidX.lifecycle}"
    const val room = "androidx.room:room-ktx:${Version.AndroidX.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Version.AndroidX.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.AndroidX.room}"
    const val paging = "androidx.paging:paging-runtime:${Version.AndroidX.paging}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Version.AndroidX.navigation}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.AndroidX.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Version.AndroidX.navigation}"
    const val material3 =  "androidx.compose.material3:material3:${Version.AndroidX.materialUi}"
    const val materialWindowSize =  "androidx.compose.material3:material3-window-size-class:${Version.AndroidX.materialUi}"
    const val fragmentKtx =  "androidx.fragment:fragment-ktx:${Version.AndroidX.fragment}"
}

object Google {
    const val material ="com.google.android.material:material:${Version.Google.material}"
    const val gson = "com.google.code.gson:gson:${Version.Google.gson}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Version.Google.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Version.Google.hilt}"
}

object Squareup {
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Version.Squareup.retrofit2}"
    const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${Version.Squareup.retrofit2}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp"
    const val okhttp3Bom = "com.squareup.okhttp3:okhttp-bom:${Version.Squareup.okhttp3}"
    const val okhttp3LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
}

object Github {
    const val glide = "com.github.bumptech.glide:glide:${Version.Github.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.Github.glide}"
}

object Jetbrains {
    const val CoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Jetbrains.kotlinxCoroutines}"
    const val CoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Jetbrains.kotlinxCoroutines}"
}

object Facebook {
    const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"
}