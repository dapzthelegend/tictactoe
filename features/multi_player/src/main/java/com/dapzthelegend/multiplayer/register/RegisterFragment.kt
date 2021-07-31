package com.dapzthelegend.multiplayer.register

import androidx.fragment.app.Fragment
import com.dapzthelegend.multiplayer.R
import com.dapzthelegend.multiplayer.databinding.FragmentRegisterBinding
import com.dapzthelegend.multiplayer.register.di.DaggerRegisterComponent
import com.dapzthelegend.multiplayer.register.di.RegisterModule
import com.dapzthelegend.ui.base.BaseFragment

/**
 * View for entering player's names.
 */
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    layoutId = R.layout.fragment_register
){
    override fun onInitDependencyInjection() {
        DaggerRegisterComponent
            .builder()
            .registerModule(RegisterModule(this))
            .build()
            .inject(this)

    }

    override fun onInitDataBinding() {
       viewBinding.viewModel = viewModel
    }

}
