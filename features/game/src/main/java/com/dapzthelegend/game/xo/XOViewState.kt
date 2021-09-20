package com.dapzthelegend.game.xo

import com.dapzthelegend.ui.base.BaseViewState

/**
 * Class for managing different view states of [XOFragment].
 *
 * @see BaseViewState
 */
sealed class XOViewState : BaseViewState {

    /**
     * Game round Ended.
     */
    object RoundEnd : XOViewState()

    /**
     * Game in round.
     */
    object InRound : XOViewState()

    // =============================================================================================
    // Public helper methods
    // =============================================================================================

    /**
     * Check if view state is [RoundEnd].
     *
     * @return True if state is [RoundEnd], else false.
     */
    fun isRoundEnd() = this is RoundEnd

    /**
     * Check if view state is [RoundIn].
     *
     * @return True if state is [InRound], else false.
     */
    fun isInRound() = this is InRound
}
