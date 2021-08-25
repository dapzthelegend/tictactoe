package com.dapzthelegend.multiplayer.register

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dapzthelegend.multiplayer.R
import com.dapzthelegend.multiplayer.databinding.FragmentRegisterBinding
import com.dapzthelegend.multiplayer.register.di.DaggerRegisterComponent
import com.dapzthelegend.multiplayer.register.di.RegisterModule
import com.dapzthelegend.ui.base.BaseFragment
import com.dapzthelegend.ui.extensions.collect

/**
 * View for entering player's names.
 */
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    layoutId = R.layout.fragment_register
) {
    private val args: RegisterFragmentArgs by navArgs()

    /**
     * Called to have the fragment instantiate the user interface view
     *
     * @param view The view returned by [BaseFragment.onCreateView]
     * @param savedInstanceState If non-null, this fragment is being reconstructed from a previous
     * state
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collect(viewModel.event, lifecycle, ::onViewEventChanged)
    }

    /**
     * Instantiate Dagger Register object graph.
     */
    override fun onInitDependencyInjection() {
        DaggerRegisterComponent
            .builder()
            .registerModule(RegisterModule(this))
            .build()
            .inject(this)
    }

    /**
     * Instantiate view binding data variables.
     */
    override fun onInitDataBinding() {
        viewModel.isMultiPlayer = args.mode == "multi"

        viewBinding.viewModel = viewModel
    }

    // =============================================================================================
    // Private collectors.
    // =============================================================================================

    /**
     * Collect latest events from the viewEvent flow.
     */
    private fun onViewEventChanged(viewEvent: RegisterViewEvent) {
        when (viewEvent) {
            is RegisterViewEvent.OpenXO -> {
                var action = RegisterFragmentDirections.actionRegisterFragmentToXoFragment(mode = args.mode)
                if (!TextUtils.isEmpty(viewEvent.player1)
                ) {
                    action = RegisterFragmentDirections.actionRegisterFragmentToXoFragment(
                        playerName1 = viewEvent.player1!!,
                        mode = args.mode
                    )
                }else if (!TextUtils.isEmpty(viewEvent.player1) &&
                    !TextUtils.isEmpty(viewEvent.player2)
                ) {
                    action = RegisterFragmentDirections.actionRegisterFragmentToXoFragment(
                        playerName1 = viewEvent.player1!!,
                        playerName2 = viewEvent.player2!!,
                        mode = args.mode
                    )
                }
                findNavController().navigate(action)
            }
            is RegisterViewEvent.ReturnToHome -> findNavController().popBackStack()
        }
    }
}
