package com.tonghannteng.dogceo.data.state

import com.tonghannteng.dogceo.data.model.DogResponse

/**
 * @author: Tonghann Teng
 * @since: 12/6/23
 *
 * A Sealed class represents response state.
 */
sealed class DogState {
    data object Loading : DogState()
    data class Success(val data: DogResponse) : DogState()
    data class Error(val error: String) : DogState()
}

/**
 * Make this Sealed class to Generic
 */
//sealed class DogState<T> {
//    class Loading<T>: DogState<T>()
//    data class Success<T>(val data: T): DogState<T>()
//    data class Error<T>(val error: String): DogState<T>()
//}
