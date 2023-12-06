package com.tonghannteng.dogceo.data.repository

import com.tonghannteng.dogceo.data.model.DogResponse
import kotlinx.coroutines.flow.Flow

/**
 * @author: Tonghann Teng
 * @since: 12/5/23
 */
interface IDogRepository {

    /**
     * Get random dog.
     */
    suspend fun getRandomDog(): Flow<DogResponse>
}
