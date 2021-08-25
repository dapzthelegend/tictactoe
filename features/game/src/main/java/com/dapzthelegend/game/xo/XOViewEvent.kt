package com.dapzthelegend.multiplayer.xo

import com.dapzthelegend.ui.base.BaseViewEvent

/**
 * Class for managing user events for [XOFragment]
 *
 * @see BaseViewEvent
 */
sealed class XOViewEvent : BaseViewEvent() {

    /**
     * Send to home.
     */
    object ReturnToHome : XOViewEvent()
}
