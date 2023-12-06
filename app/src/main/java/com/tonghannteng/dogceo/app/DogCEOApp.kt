package com.tonghannteng.dogceo.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author: Tonghann Teng
 * @since: 12/5/23
 */
@HiltAndroidApp
class DogCEOApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
