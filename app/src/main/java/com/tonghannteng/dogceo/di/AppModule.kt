package com.tonghannteng.dogceo.di

import com.tonghannteng.dogceo.data.repository.DogRepository
import com.tonghannteng.dogceo.data.repository.IDogRepository
import com.tonghannteng.dogceo.data.service.IDogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideRetrofit(): IDogService = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IDogService::class.java)

    @Provides
    @Singleton
    fun injectRepository(dogService: IDogService) =
        DogRepository(dogCEOService = dogService) as IDogRepository

    companion object {
        const val BASE_URL = "https://dog.ceo/api/breeds/"
    }
}
