package com.coroutineshub.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.alty.data.api.exception.DataException
import co.alty.data.api.exception.DataHttpException
import co.alty.data.api.exception.DataNetworkException
import co.alty.data.api.exception.DataUnexpectedException
import co.alty.domain.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

/**
 * Base view model
 * */
open class BaseViewModel : ViewModel() {

  protected val parentJob = Job()

  private val toastErrorMutableLiveData = SingleLiveEvent<String>()

  val toastErrorLiveData: LiveData<String> = toastErrorMutableLiveData

  override fun onCleared() {
    super.onCleared()
    parentJob.cancelChildren()
  }

  fun Throwable.consumeException() {
    when (this) {
      is DataException -> {
        when (this) {
          is DataHttpException -> {
            toastErrorMutableLiveData.value = mes
          }
          is DataNetworkException -> {
            toastErrorMutableLiveData.value = mes
          }
          is DataUnexpectedException -> {
            toastErrorMutableLiveData.value = mes
          }
        }
      }
      else -> {
        toastErrorMutableLiveData.value = message
      }
    }
  }
}