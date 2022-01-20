package com.coroutineshub

import com.coroutineshub.di.BaseViewModelFactory
import com.coroutineshub.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
) : BaseViewModelFactory<MainViewModel>() {

  override fun viewModel() = MainViewModel()
}

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

}