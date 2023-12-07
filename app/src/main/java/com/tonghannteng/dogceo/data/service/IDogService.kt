package com.tonghannteng.dogceo.data.service

import com.tonghannteng.dogceo.data.model.DogResponse
import retrofit2.http.GET

/**
 * @author: Tonghann Teng
 * @since: 12/5/23
 */
interface IDogService {

    @GET("image/random")
    suspend fun getRandomDog(): DogResponse
}
