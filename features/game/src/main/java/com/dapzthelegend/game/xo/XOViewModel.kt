package com.dapzthelegend.multiplayer.xo

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.dapzthelegend.multiplayer.xo.game.GameManager
import com.dapzthelegend.multiplayer.xo.game.Player
import com.dapzthelegend.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * View model for preparing and managing data, state, and event for [XOFragment]
 *
 * @see BaseViewModel
 */
class XOViewModel : BaseViewModel<XOViewEvent>() {

    val gameManager = GameManager()
    var x = ""
    var o = ""
    val message: MutableLiveData<String> = MutableLiveData()

    val state = Transformations.map(gameManager.winner) {
        when (it) {
            Player.PLAYER_0 -> {
                val name = if (gameManager.player.value == Player.PLAYER_1) {
                    x
                } else {
                    o
                }
                message.postValue("$name's turn")
                XOViewState.InRound
            }
            Player.PLAYER_DRAW -> {
                message.postValue("Draw")
                XOViewState.RoundEnd
            }
            Player.PLAYER_1 -> {
                message.postValue("$x Won!")
                XOViewState.RoundEnd
            }
            Player.PLAYER_2 -> {
                message.postValue("$o Won!")
                XOViewState.RoundEnd
            }
            else -> XOViewState.InRound
        }
    }

    // =============================================================================================
    // Public methods
    // =============================================================================================

    /**
     * Called when play again is clicked.
     */
    fun onPlayAgainClicked() {
        gameManager.resetGame()
    }

    /**
     * Called when home button is clicked.
     */
    fun onHomeClicked() {
        viewModelScope.launch {
            event.emit(XOViewEvent.ReturnToHome)
        }
    }

    /**
     * Called when user makes a new move.
     */
    fun onBoardClicked(view: View) {
        val id = view.tag.toString().toInt()
        val row = id / NUMBER_OF_ROWS_AND_COL
        val col = id % NUMBER_OF_ROWS_AND_COL
        gameManager.played(row, col)

        viewModelScope.launch {
            delay(DELAY)
            gameManager.checkWinner()
        }
    }

    /**
     * Called when user makes a new move.
     */
    fun onBoardClicked(row:Int, col:Int) {
        gameManager.played(row, col)

        viewModelScope.launch {
            delay(DELAY)
            gameManager.checkWinner()
        }
    }

    companion object {
        private const val NUMBER_OF_ROWS_AND_COL = 3
        private const val DELAY: Long = 100
    }
}
