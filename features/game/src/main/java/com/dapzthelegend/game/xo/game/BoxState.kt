package com.dapzthelegend.multiplayer.xo.game

/**
 * Class for managing XO Box view state.
 */
sealed class BoxState {
    /**
     * No move has been made.
     */
    object Empty : BoxState()

    /**
     * An X has been played.
     */
    object X : BoxState()

    /**
     * An O has been played.
     */
    object O : BoxState()

    /**
     * Winning state.
     */
    object Won : BoxState()
}
