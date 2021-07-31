package com.dapzthelegend.home

import com.dapzthelegend.ui.base.BaseViewEvent

/**
 * Different interaction events for [HomeFragment]
 */
sealed class HomeViewEvent : BaseViewEvent() {

    /**
     * Single player game selected.
     */
    object SinglePlayer : HomeViewEvent()

    /**
     * Multi player game selected.
     */
    object MultiPlayer : HomeViewEvent()
}
