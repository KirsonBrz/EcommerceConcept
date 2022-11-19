@Suppress("MemberVisibilityCanBePrivate", "unused")
object Deps {
    private const val kotlinVersion = "1.7.20"
    private const val agpVersion = "7.3.1"
    private const val googleServicesVersion = "4.3.10"
    private const val supportLibVersion = "1.1.0"
    private const val coroutinesVersion = "1.5.1"
    private const val retrofit_version = "2.9.0"
    private const val interceptor_version = "4.10.0"
    private const val composeVersion = "1.3.0"
    private const val accompanistVersion = "0.18.0"
    private const val lifecycle_version = "2.5.1"
    private const val room_version = "2.4.3"
    private const val liveData_version = "2.6.0-alpha03"
    private const val dagger_version = "2.44"
    private const val hilt_compiler_version = "1.0.0"
    private const val hilt_navigation_compose_version = "1.0.0"

    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
    const val kotlinCompiler = "org.jetbrains.kotlin:kotlin-compiler-embeddable:$kotlinVersion"


    const val kotlinCoroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val kotlinCoroutinesRx2 =
        "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$coroutinesVersion"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"


    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    const val composeMaterialIcons = "androidx.compose.material:material-icons-core:$composeVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val composeRuntime = "androidx.compose.runtime:runtime:$composeVersion"
    const val composeRuntimeLiveData = "androidx.compose.runtime:runtime-livedata:$composeVersion"
    const val composeActivity = "androidx.activity:activity-compose:1.6.1"
    const val composeNavigation = "androidx.navigation:navigation-compose:2.5.3"

    const val coilCompose = "io.coil-kt:coil-compose:2.2.2"

    const val accompanistInsets = "com.google.accompanist:accompanist-insets:$accompanistVersion"
    const val accompanistSystemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
    const val accompanistPager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
    const val accompanistPagerIndicators =
        "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion"
    const val accompanistFlowLayout =
        "com.google.accompanist:accompanist-flowlayout:$accompanistVersion"


    const val googleServices = "com.google.gms:google-services:$googleServicesVersion"

    const val androidGradlePlugin = "com.android.tools.build:gradle:$agpVersion"
    const val androidDaggerHiltPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:$dagger_version"

    const val timber = "com.jakewharton.timber:timber:5.0.1"

    val coreLibs = arrayOf(
        kotlinStdLib,
        kotlinReflect,
        kotlinCoroutinesCore,
        kotlinCoroutinesAndroid,
        kotlinCoroutinesRx2,
        "com.michael-bull.kotlin-result:kotlin-result:1.1.9",
        "com.jakewharton.rxrelay2:rxrelay:2.1.1",
    )


    const val supportAnnotations = "androidx.annotation:annotation:$supportLibVersion"
    val supportCoreLibs = arrayOf(
        supportAnnotations,
        "androidx.core:core:1.9.0",
        "androidx.core:core-ktx:1.9.0"
    )
    const val supportActivity = "androidx.activity:activity-ktx:1.2.0-rc01"
    val supportUiLibs = arrayOf(
        "androidx.appcompat:appcompat:1.5.1",
        supportActivity,
        "androidx.compose.material3:material3:1.0.0"
    )
    const val supportPreferences = "androidx.preference:preference-ktx:1.1.1"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val retrofitJson =  "com.squareup.retrofit2:converter-gson:$retrofit_version"
    //okhttp3
    const val okhttp3 = "com.squareup.okhttp3:logging-interceptor:$interceptor_version"


    const val firebaseCore = "com.google.firebase:firebase-core:18.0.2"
    const val firebaseAuth = "com.firebaseui:firebase-ui-auth:7.2.0"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging:21.0.1"

    const val firebaseAppDistributionPlugin =
        "com.google.firebase:firebase-appdistribution-gradle:2.1.2"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:19.0.0"

    const val googleServicesPlugin = "com.google.gms:google-services:4.3.8"


    // Hilt and Dagger
    const val kaptDaggerHilt = "com.google.dagger:hilt-compiler:$dagger_version"
    const val implementationHiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose_version"
    const val implementationHilt = "com.google.dagger:hilt-android:$dagger_version"
    const val kaptDaggerHiltAndroid = "com.google.dagger:hilt-android-compiler:$dagger_version"


    const val testImplementationJunit = "junit:junit:4.13.2"
    const val androidTestImplementationJunit = "androidx.test.ext:junit:1.1.3"
    const val androidTestImplementationEspresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val androidTestImplementationComposeUi =
        "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val debugImplementation = "androidx.compose.ui:ui-tooling:$composeVersion"

    //Lifecycle + Compose ViewModel
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version"
    const val lifecycleViewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version"
    const val lifecycle = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
}