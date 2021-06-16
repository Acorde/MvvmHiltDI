package com.igor.mvvmhiltdi.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.igor.mvvmhiltdi.repository.MainRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class MainViewModel @Inject constructor(
//    private val mainRepository : MainRepository,
//    @Assisted private val savedStateHandle: SavedStateHandle) :
//    ViewModel() {
//
//}