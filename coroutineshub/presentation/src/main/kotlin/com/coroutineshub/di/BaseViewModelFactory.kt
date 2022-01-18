package com.coroutineshub.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coroutineshub.ui.base.BaseViewModel

/**
 * Factory for provide view model
 * */
abstract class BaseViewModelFactory<VM : BaseViewModel> : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return viewModel() as T
  }

  abstract fun viewModel(): VM
}