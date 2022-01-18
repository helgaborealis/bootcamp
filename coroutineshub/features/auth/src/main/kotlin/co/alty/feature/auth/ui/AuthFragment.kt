package co.alty.feature.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import co.alty.feature.auth.databinding.FragmentAuthBinding
import co.alty.feature.auth.di.DaggerAuthComponent
import com.coroutineshub.di.PresentationModuleDependencies
import com.coroutineshub.ui.base.BaseFragment
import dagger.hilt.android.EntryPointAccessors


class AuthFragment : BaseFragment<FragmentAuthBinding, AuthViewModel>() {

  override fun initDeps() {
    val coreModuleDependencies = EntryPointAccessors.fromApplication(
      requireActivity().applicationContext,
      PresentationModuleDependencies::class.java
    )

    DaggerAuthComponent.factory()
      .create(
        dependentModule = coreModuleDependencies,
        fragment = this
      )
      .inject(this)
  }

  override fun getFragmentBinding(inflater: LayoutInflater) =
    FragmentAuthBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initViews()
  }

  private fun initViews() {
    binding?.apply {
      // todo init views
    }
  }
}