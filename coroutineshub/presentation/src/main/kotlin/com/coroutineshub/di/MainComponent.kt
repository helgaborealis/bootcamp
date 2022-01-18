package com.coroutineshub.di

import androidx.fragment.app.Fragment
import com.coroutineshub.MainFragment
import dagger.BindsInstance
import dagger.Component

@Component(
  modules = [MainModule::class]
)
interface MainComponent {
  fun inject(fragment: MainFragment)

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance fragment: Fragment
    ): MainComponent
  }
}