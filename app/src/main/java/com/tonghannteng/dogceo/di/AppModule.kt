package com.tonghannteng.dogceo.di

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.tonghannteng.dogceo.data.repository.DogRepository
import com.tonghannteng.dogceo.data.repository.IDogRepository
import com.tonghannteng.dogceo.data.service.IDogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * @author: Tonghann Teng
 * @since: 12/5/23
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext appContext: Context): IDogService = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildOkHttp(appContext))
        .build()
        .create(IDogService::class.java)

    private fun buildOkHttp(appContext: Context): OkHttpClient {
        val plugin = AndroidFlipperClient
            .getInstance(appContext)
            .getPluginByClass(NetworkFlipperPlugin::class.java)
        return OkHttpClient.Builder()
            .addNetworkInterceptor(FlipperOkhttpInterceptor(plugin))
            .build()
    }


    @Provides
    @Singleton
    fun injectRepository(dogService: IDogService) =
        DogRepository(dogCEOService = dogService) as IDogRepository

    companion object {
        const val BASE_URL = "https://dog.ceo/api/breeds/"
    }
}
