package com.metalist.bookreader.presentation.application

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.metalist.bookreader.utils.CrashlyticsTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BookApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree(), CrashlyticsTree())
        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)
    }
}