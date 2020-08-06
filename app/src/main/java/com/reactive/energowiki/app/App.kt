package com.reactive.energowiki.app

import androidx.multidex.MultiDexApplication
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.reactive.energowiki.BuildConfig
import com.reactive.energowiki.di.networkModule
import com.reactive.energowiki.di.sharedPrefModule
import com.reactive.energowiki.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : MultiDexApplication() {

    companion object {
        private lateinit var app: App
        fun get(): App = app
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        initFirebase()

        initLogger()

        initKoin()
    }

    private fun initFirebase() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    private fun initKoin() {

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    sharedPrefModule
                )
            )
        }
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}