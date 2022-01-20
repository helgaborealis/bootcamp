package com.coroutineshub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.coroutineshub.databinding.FragmentMainBinding
import com.coroutineshub.di.DaggerMainComponent
import com.coroutineshub.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

  override fun initDeps() {
    DaggerMainComponent.factory()
      .create(this)
      .inject(this)
  }

  override fun getFragmentBinding(inflater: LayoutInflater) = FragmentMainBinding.inflate(inflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    //todo check auth flag
    val action = MainFragmentDirections.actionMainFragmentToAuth()
    findNavController().navigate(action)

  }
}
