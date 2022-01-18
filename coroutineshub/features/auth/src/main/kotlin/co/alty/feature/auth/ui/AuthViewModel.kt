package co.alty.feature.auth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coroutineshub.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
  private val coroutineScope: CoroutineScope
) : BaseViewModel() {

  private val _loginProgressLiveData = MutableLiveData<Boolean>()

  val loginProgressLiveData: LiveData<Boolean> = _loginProgressLiveData
}