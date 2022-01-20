package co.alty.feature.auth.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.alty.feature.auth.ui.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@InstallIn(FragmentComponent::class)
@Module
class AuthModule {

  @Provides
  fun provideCoroutineScope() = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

  @Provides
  fun authViewModelProvider(
    fragment: Fragment,
    factory: LoginViewModelFactory
  ): AuthViewModel = ViewModelProvider(fragment, factory).get(AuthViewModel::class.java)

}