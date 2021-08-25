package com.dapzthelegend.multiplayer.register

import com.dapzthelegend.ui.base.BaseViewEvent

/**
 * Class for managing view interaction events for [RegisterFragment]
 *
 * @see BaseViewEvent
 */
sealed class RegisterViewEvent : BaseViewEvent() {

    /**
     * Start multiplayer game.
     */
    data class OpenXO(
        val player1: String?,
        val player2: String?
    ) : RegisterViewEvent()

    /**
     * Return to home screen.
     */
    object ReturnToHome : RegisterViewEvent()
}
