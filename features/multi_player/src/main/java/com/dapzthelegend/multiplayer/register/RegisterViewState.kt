package com.dapzthelegend.multiplayer.register

import com.dapzthelegend.ui.base.BaseViewState

/**
 * Class for managing different view states of [RegisterFragment]
 */
sealed class RegisterViewState : BaseViewState {

    /**
     * No name has been entered.
     */
    object NoNameEntered : RegisterViewState()

    /**
     * Name entered.
     */
    object NameEntered : RegisterViewState()

    // =============================================================================================
    // Public helper methods
    // =============================================================================================

    /**
     * Check if current view state is [NoNameEntered]
     *
     * @return True if current view state is [NoNameEntered], else
     * false
     */
    fun isNoNameEnteredYEt() = this is NoNameEntered

    /**
     * Check if current view state is [NameEntered]
     *
     * @return True if current view state is [NoNameEntered], else
     * false
     */
    fun isNameEntered() = this is NameEntered
}
