package co.alty.feature.auth.di

import co.alty.feature.auth.ui.AuthViewModel
import com.coroutineshub.di.BaseViewModelFactory
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
  private val coroutineScope: CoroutineScope
) : BaseViewModelFactory<AuthViewModel>() {
  override fun viewModel() = AuthViewModel(coroutineScope)
}