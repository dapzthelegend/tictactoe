package com.dapzthelegend.game.register

import com.dapzthelegend.ui.base.BaseViewState

/**
 * Class for managing different view states of [RegisterFragment]
 *
 * @param isMultiPlayer Game mode.
 */
sealed class RegisterViewState(
    val isMultiPlayer: Boolean
) : BaseViewState {

    /**
     * No name has been entered.
     *
     * @param isMultiPlayer Game mode.
     */
    class NoNameEntered(isMultiPlayer: Boolean) : RegisterViewState(isMultiPlayer)

    /**
     * Name entered.
     *
     * @param isMultiPlayer Game mode.
     */
    class NameEntered(isMultiPlayer: Boolean) : RegisterViewState(isMultiPlayer)

    // =============================================================================================
    // Public helper methods
    // =============================================================================================

    /**
     * Check if current view state is [NoNameEntered]
     *
     * @return True if current view state is [NoNameEntered], else
     * false
     */
    fun isNoNameEnteredYet() = this is NoNameEntered

    /**
     * Check if current view state is [NameEntered]
     *
     * @return True if current view state is [NoNameEntered], else
     * false
     */
    fun isNameEntered() = this is NameEntered
}
