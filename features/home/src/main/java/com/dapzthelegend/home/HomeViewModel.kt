package com.dapzthelegend.home

import androidx.lifecycle.viewModelScope
import com.dapzthelegend.ui.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Viewmodel for managing states and event of [HomeFragment]
 *
 * @see BaseViewModel
 */
class HomeViewModel : BaseViewModel<HomeViewEvent>() {

    /**
     * Called to send interaction event to open single player feature.
     */
    fun onSinglePlayerClicked() {
        viewModelScope.launch {
            event.emit(HomeViewEvent.SinglePlayer)
        }
    }

    /**
     * Called to send interaction event to open multi player feature.
     */
    fun onMultiPlayerClicked() {
        viewModelScope.launch {
            event.emit(HomeViewEvent.MultiPlayer)
        }
    }
}
