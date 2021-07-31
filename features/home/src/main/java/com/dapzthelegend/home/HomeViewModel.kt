package com.dapzthelegend.home

import com.dapzthelegend.ui.base.BaseViewModel

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
        event.postValue(HomeViewEvent.SinglePlayer)
    }

    /**
     * Called to send interaction event to open multi player feature.
     */
    fun onMultiPlayerClicked() {
        event.postValue(HomeViewEvent.MultiPlayer)
    }
}
