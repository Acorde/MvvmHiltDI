package com.igor.mvvmhiltdi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.mvvmhiltdi.modules.Blog
import com.igor.mvvmhiltdi.repository.MainRepository
import com.igor.mvvmhiltdi.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogEvent -> mainRepository.getBlog()
                    .onEach { dataState -> _dataState.value = dataState }
                    .launchIn(viewModelScope)

                is MainStateEvent.None -> {}
            }
        }
    }

}

sealed class MainStateEvent {
    object GetBlogEvent : MainStateEvent()
    object None : MainStateEvent()
}