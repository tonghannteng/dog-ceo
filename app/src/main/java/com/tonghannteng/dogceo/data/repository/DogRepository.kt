package com.tonghannteng.dogceo.data.repository

import com.tonghannteng.dogceo.data.model.DogResponse
import com.tonghannteng.dogceo.data.service.IDogService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 12/5/23
 */
class DogRepository @Inject constructor(
    private val dogCEOService: IDogService
) : IDogRepository {

    override suspend fun getRandomDog(): Flow<DogResponse> {
        return flow {
            dogCEOService.getRandomDog()
        }
    }
}
