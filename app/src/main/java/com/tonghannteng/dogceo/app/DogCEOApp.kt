package com.tonghannteng.dogceo.app

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp

/**
 * @author: Tonghann Teng
 * @since: 12/5/23
 */
@HiltAndroidApp
class DogCEOApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        val client = AndroidFlipperClient.getInstance(this)
        client.addPlugin(NetworkFlipperPlugin())
        client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
        client.start()
    }
}
