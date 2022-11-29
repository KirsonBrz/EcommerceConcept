@Suppress("MemberVisibilityCanBePrivate", "unused")
object Deps {


    //Kotlin Core
    private const val kotlin_version = "1.7.20"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"
    const val kotlinCompiler = "org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlin_version"


    //Google Services
    private const val google_services_version = "4.3.10"
    const val googleServices = "com.google.gms:google-services:$google_services_version"

    //Plugins
    private const val hilt_version = "2.44"
    private const val agp_version = "7.3.1"
    const val androidGradlePlugin = "com.android.tools.build:gradle:$agp_version"
    const val androidDaggerHiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"

    //Coroutines
    private const val coroutines_version = "1.5.1"
    const val kotlinCoroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

    const val kotlinCoroutinesGuava = "org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.6.0-RC"

    val coreLibs = arrayOf(
        kotlinStdLib,
        kotlinReflect,
        kotlinCoroutinesCore,
        kotlinCoroutinesAndroid,
    )


    //Support Annotations
    private const val support_lib_version = "1.1.0"
    const val supportAnnotations = "androidx.annotation:annotation:$support_lib_version"

    //Core libs
    private const val kotlin_core_version = "1.9.0"
    val supportCoreLibs = arrayOf(
        supportAnnotations,
        "androidx.core:core:$kotlin_core_version",
        "androidx.core:core-ktx:$kotlin_core_version"
    )

    //UI libs
    private const val appcompat_version = "1.5.1"
    val supportUiLibs = arrayOf(
        "androidx.appcompat:appcompat:$appcompat_version",
        "androidx.compose.material3:material3:1.0.0"
    )


    //Compose
    private const val composeVersion = "1.3.1"
    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
    const val composeUtil = "androidx.compose.ui:ui-util:$composeVersion"
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    const val composeMaterialIcons = "androidx.compose.material:material-icons-core:$composeVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val composeRuntime = "androidx.compose.runtime:runtime:$composeVersion"
    const val composeRuntimeLiveData = "androidx.compose.runtime:runtime-livedata:$composeVersion"
    const val composeActivity = "androidx.activity:activity-compose:1.6.1"
    const val composeNavigation = "androidx.navigation:navigation-compose:2.5.3"

    //Lifecycle + Compose ViewModel
    private const val lifecycle_version = "2.5.1"

    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    const val lifecycleViewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version"
    const val lifecycleViewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version"
    const val lifecycle = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"


    //Async Image
    const val coilCompose = "io.coil-kt:coil-compose:2.2.2"

    //Accompanist
    private const val accompanist_version = "0.27.1"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:$accompanist_version"
    const val accompanistSystemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:$accompanist_version"
    const val accompanistPermissions =
        "com.google.accompanist:accompanist-permissions:$accompanist_version"
    const val accompanistInsets = "com.google.accompanist:accompanist-insets:$accompanist_version"
    const val accompanistPager = "com.google.accompanist:accompanist-pager:$accompanist_version"
    const val accompanistPagerIndicators =
        "com.google.accompanist:accompanist-pager-indicators:$accompanist_version"

    //Rating bar
    const val ratingBar = "com.github.a914-gowtham:compose-ratingbar:1.2.4"


    //Camera
    private const val camera_version = "1.1.0"
    const val cameraCore = "androidx.camera:camera-core:$camera_version"
    const val camera2 = "androidx.camera:camera-camera2:$camera_version"
    const val cameraLifecycle = "androidx.camera:camera-lifecycle:$camera_version"
    const val cameraView = "androidx.camera:camera-view:$camera_version"


    //Timber
    const val timber = "com.jakewharton.timber:timber:5.0.1"


    //Retrofit
    private const val retrofit_version = "2.9.0"
    private const val interceptor_version = "4.10.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val retrofitJson = "com.squareup.retrofit2:converter-gson:$retrofit_version"

    //okhttp3
    const val okhttp3 = "com.squareup.okhttp3:logging-interceptor:$interceptor_version"


    //Firebase
    const val firebaseCore = "com.google.firebase:firebase-core:18.0.2"
    const val firebaseAuth = "com.firebaseui:firebase-ui-auth:7.2.0"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging:21.0.1"

    const val firebaseAppDistributionPlugin =
        "com.google.firebase:firebase-appdistribution-gradle:2.1.2"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:19.0.0"

    const val googleServicesPlugin = "com.google.gms:google-services:4.3.8"


    // Hilt
    private const val hilt_compiler_version = "1.0.0"
    private const val hilt_navigation_compose_version = "1.0.0"

    const val kaptDaggerHilt = "com.google.dagger:hilt-compiler:$hilt_version"
    const val implementationHiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose_version"
    const val implementationHilt = "com.google.dagger:hilt-android:$hilt_version"
    const val kaptDaggerHiltAndroid = "com.google.dagger:hilt-android-compiler:$hilt_version"


    //Test dependencies
    const val testImplementationJunit = "junit:junit:4.13.2"
    const val androidTestImplementationJunit = "androidx.test.ext:junit:1.1.3"
    const val androidTestImplementationEspresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val androidTestImplementationComposeUi =
        "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val debugImplementation = "androidx.compose.ui:ui-tooling:$composeVersion"


}