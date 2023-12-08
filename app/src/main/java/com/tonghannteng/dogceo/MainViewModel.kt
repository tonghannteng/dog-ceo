package com.tonghannteng.dogceo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonghannteng.dogceo.data.model.DogResponse
import com.tonghannteng.dogceo.data.repository.DogRepository
import com.tonghannteng.dogceo.data.repository.IDogRepository
import com.tonghannteng.dogceo.data.state.DogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Tonghann Teng
 * @since: 12/6/23
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IDogRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DogState>(DogState.Loading)
    val state = _state.asStateFlow()

//    init {
//        getRandomDog()
//    }

    /**
     * Get Random dog.
     */
    fun getRandomDog() {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = DogState.Loading
            repository.getRandomDog()
                .flowOn(Dispatchers.IO)
                .catch {
                    _state.value = DogState.Error("Error Message")
                }
                .collect {
                    _state.value = DogState.Success(data = it)
                }
        }
    }
}
