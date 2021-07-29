package com.example.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Base fragment class to standardize and simplify the initialization of this component
 *
 * @param layoutId The resource id for the fragment layout
 * @see Fragment
 */
abstract class BaseFragment<B : ViewDataBinding, M : ViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {

    @Inject
    lateinit var viewModel: M
    lateinit var viewBinding: B

    /**
     * Called to initialize the dependency graph when the fragment is attached
     */
    abstract fun onInitDependencyInjection()

    /**
     * Called to init data binding methods when fragment view is created
     */
    abstract fun onInitDataBinding()

    /**
     * Called to instantiate the fragment user interface view
     *
     * @param inflater Object responsible for inflating views in the fragment
     * @param container If non-null, this is the fragment view that the fragment should
     * attached to. Th fragment should not add the view itself but this can be used to
     * generate the layoutparams of the views
     * @param savedInstanceState if non-null, the fragment is resuming from a previous state
     *
     * @return Return the view for the fragment's UI or null
     *
     * @see Fragment.OnCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.lifecycleOwner = this
        return viewBinding.root
    }

    /**
     * Called when the fragment is first attached to the context
     *
     * @param context The application context
     *
     * @see Fragment.onAttach
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInitDependencyInjection()
    }

    /**
     * Called when the fragment's UI is first created
     *
     * @param view Return the view created by [Fragment.onCreateView]
     * @param savedInstanceState If non-null, the fragment is being reconstructed from
     * a previous given state.
     *
     * @see Fragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
    }

    /**
     * Return the [AppCompatActivity] this fragment is associated with
     *
     * @throws IllegalStateException if not currently associated with an activity or
     * context
     * @throws TypeCastException if current activity does not extend [AppCompatActivity]
     *
     * @see requireActivity
     */
    fun requireCompatActivity(): AppCompatActivity {
        requireActivity()
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            return activity
        } else {
            throw TypeCastException("Main Activity should extend AppCompatActivity")
        }
    }
}
