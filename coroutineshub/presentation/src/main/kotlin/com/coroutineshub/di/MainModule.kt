package com.coroutineshub.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coroutineshub.MainViewModel
import com.coroutineshub.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class MainModule {

  @Provides
  fun loginViewModelProvider(
    fragment: Fragment,
    factory: MainViewModelFactory
  ) = ViewModelProvider(fragment, factory).get(MainViewModel::class.java)
}