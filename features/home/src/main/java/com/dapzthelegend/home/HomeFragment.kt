package com.dapzthelegend.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dapzthelegend.home.databinding.FragmentHomeBinding
import com.dapzthelegend.home.di.DaggerHomeComponent
import com.dapzthelegend.home.di.HomeModule
import com.dapzthelegend.ui.base.BaseFragment
import com.dapzthelegend.ui.extensions.observe

/**
 * Home view displaying the single and multi player option.
 *
 * @see BaseFragment
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
) {

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned from [Fragment.onCreateView]
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.event, ::onViewEventChanged)
    }

    /**
     * Initialize home dependency object graph.
     */
    override fun onInitDependencyInjection() {
        DaggerHomeComponent
            .builder()
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view binding variables
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    // =============================================================================================
    // Private observer methods.
    // =============================================================================================

    private fun onViewEventChanged(event: HomeViewEvent) {
        when (event) {
            HomeViewEvent.MultiPlayer -> {
                val action = HomeFragmentDirections.actionHomeFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
            HomeViewEvent.SinglePlayer -> {
            }
        }
    }
}
