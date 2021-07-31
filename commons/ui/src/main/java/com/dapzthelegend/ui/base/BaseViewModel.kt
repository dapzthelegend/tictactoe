package com.dapzthelegend.ui.base

import androidx.lifecycle.ViewModel
import com.dapzthelegend.ui.livedata.SingleLiveData

/**
 * Base view model for managing and preparing data for Fragments.
 *
 * @see ViewModel
 */
abstract class BaseViewModel<T : BaseViewEvent> : ViewModel() {
    val event = SingleLiveData<T>()
}
