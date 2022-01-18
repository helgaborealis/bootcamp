package com.coroutineshub.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.dynamicfeatures.DynamicExtras
import androidx.navigation.dynamicfeatures.DynamicInstallMonitor
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import javax.inject.Inject

/**
 * Fragment with [DynamicInstallMonitor] to navigate with dynamic
 * features based on [SplitInstallSessionStatus]
 */


abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

  @Inject
  lateinit var viewModel: VM

  protected var binding: VB? = null

  abstract fun getFragmentBinding(inflater: LayoutInflater): VB

  abstract fun initDeps()

  override fun onCreate(savedInstanceState: Bundle?) {
    initDeps()
    super.onCreate(savedInstanceState)
  }

  final override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = getFragmentBinding(inflater)
    return binding?.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.toastErrorLiveData.observe(viewLifecycleOwner, {
      Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    })
  }

  fun back() {
    findNavController().popBackStack()
  }

  protected open fun beforeDestroyedView() {}

  final override fun onDestroyView() {
    super.onDestroyView()
    beforeDestroyedView()
    binding = null
  }

  fun navigateWithInstallMonitor(
    navController: NavController,
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
  ) {

    val installMonitor = DynamicInstallMonitor()

    navController.navigate(
      resId,
      args,
      navOptions,
      DynamicExtras(installMonitor)
    )

    println("DynamicInstallFragment isInstallRequired: ${installMonitor.isInstallRequired}")

    if (installMonitor.isInstallRequired) {

      installMonitor.status.observe(
        viewLifecycleOwner,
        object : Observer<SplitInstallSessionState> {

          override fun onChanged(sessionState: SplitInstallSessionState) {

            when (sessionState.status()) {

              SplitInstallSessionStatus.INSTALLED -> {
                // Call navigate again here or after user taps again in the UI:
                navController.navigate(
                  resId,
                  args,
                  navOptions,
                  DynamicExtras(installMonitor)
                )
              }
              SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
              }

              // Handle all remaining states:
              SplitInstallSessionStatus.FAILED -> {
              }
              SplitInstallSessionStatus.CANCELED -> {
              }
            }

            if (sessionState.hasTerminalStatus()) {
              installMonitor.status.removeObserver(this)
            }
          }
        }
      )
    }
  }
}
