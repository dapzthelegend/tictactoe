package com.dapzthelegend.multiplayer.xo.game

import androidx.lifecycle.MutableLiveData
import com.dapzthelegend.ui.livedata.SingleLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import kotlin.random.Random.Default.nextInt

/**
 * Class that manages the tictactoe game instance.
 */
class GameManager {
    val game: ArrayList<ArrayList<MutableLiveData<BoxState>>> = ArrayList()
    var player: MutableLiveData<Player> = MutableLiveData(Player.PLAYER_1)
    val winner: SingleLiveData<Player> = SingleLiveData()
    var round = ZERO
    val scoreX: MutableLiveData<Int> = MutableLiveData(ZERO)
    val scoreO: MutableLiveData<Int> = MutableLiveData(ZERO)
    val winStates = arrayOf(
        arrayOf(ZERO, ONE, TWO),
        arrayOf(THREE, FOUR, FIVE),
        arrayOf(SIX, SEVEN, EIGHT),
        arrayOf(ZERO, ONE, TWO),
        arrayOf(ZERO, THREE, SIX),
        arrayOf(ONE, FOUR, SEVEN),
        arrayOf(TWO, FIVE, EIGHT),
        arrayOf(ZERO, FOUR, EIGHT),
        arrayOf(TWO, FOUR, SIX)
    )

    /**
     * Set the tictactoe board with an Empty BoxState.
     */
    init {
        for (i in 0..2) {
            game.add(ArrayList())
            for (j in 0..2) {
                game[i].add(MutableLiveData(BoxState.Empty))
            }
        }
        winner.postValue(Player.PLAYER_0)
    }

    /**
     * Called when a player makes a move.
     */
    fun played(row: Int, col: Int) {
        if (game[row][col].value == BoxState.Empty && winner.value == Player.PLAYER_0) {
            round++
            if (player.value == Player.PLAYER_1) {
                game[row][col].postValue(BoxState.X)
            } else {
                game[row][col].postValue(BoxState.O)
            }
            switchPlayer()
        }
    }

    /**
     * Called to switch player's turn after a move.
     */
    private fun switchPlayer() {
        player.value = player.value.let {
            val nextPlayer = if (it == Player.PLAYER_2) {
                Player.PLAYER_1
            } else {
                Player.PLAYER_2
            }
            nextPlayer
        }
    }

    /**
     * Check if game is in a win state
     */
    fun checkWinner() {
        for (arr in winStates) {
            if (game[arr[ZERO] / ROW_COL_NUMBER][arr[ZERO] % ROW_COL_NUMBER].value ==
                game[arr[ONE] / ROW_COL_NUMBER][arr[ONE] % ROW_COL_NUMBER].value &&
                game[arr[ZERO] / ROW_COL_NUMBER][arr[ZERO] % ROW_COL_NUMBER].value ==
                game[arr[TWO] / ROW_COL_NUMBER][arr[TWO] % ROW_COL_NUMBER].value &&
                game[arr[ZERO] / ROW_COL_NUMBER][arr[ZERO] % ROW_COL_NUMBER].value != BoxState.Empty
            ) {
                game[arr[ZERO] / ROW_COL_NUMBER][arr[ZERO] % ROW_COL_NUMBER].value = BoxState.Won
                game[arr[ONE] / ROW_COL_NUMBER][arr[ONE] % ROW_COL_NUMBER].value = BoxState.Won
                game[arr[TWO] / ROW_COL_NUMBER][arr[TWO] % ROW_COL_NUMBER].value = BoxState.Won

                if (player.value == Player.PLAYER_1) {
                    winner.postValue(Player.PLAYER_2)
                    scoreO.postValue(scoreO.value!! + INCREMENT_NUM)
                } else {
                    winner.postValue(Player.PLAYER_1)
                    scoreX.postValue(scoreX.value!! + INCREMENT_NUM)
                }
            }
        }
        if (round == TOTAL_NUMBER_OF_BOXES) {
            winner.postValue(Player.PLAYER_DRAW)
        }
    }

    /**
     * Reset game to initial state.
     */
    fun resetGame() {
        round = 0
        for (i in 0..2) {
            for (j in 0..2) {
                game[i][j].value = BoxState.Empty
            }
        }
        winner.postValue(Player.PLAYER_0)
        player.value = player.value
    }

    /**
     * Check board for available moves.
     *
     * @param board The tictactoe board.
     * @return True if there is at least one move left, else false.
     */
    fun isMovesLeft(board:Array<Array<BoxState>>): Boolean{
        for(row in board){
            for (col in row){
                if (col == BoxState.Empty){
                    return true
                }
            }
        }
        return false
    }

    /**
     * Check to evaluate the board.
     *
     * @param board The tictactoe board.
     * @return MAX_NUMBER if ai wins, MIN_NUMBER if human wins and DRAW if there is no winner.
     */
    fun evaluate(board: Array<Array<BoxState>> ): Int {
        for (arr in winStates) {
            if (board[arr[ZERO] / ROW_COL_NUMBER][arr[ZERO] % ROW_COL_NUMBER] ==
                board[arr[ONE] / ROW_COL_NUMBER][arr[ONE] % ROW_COL_NUMBER] &&
                board[arr[ZERO] / ROW_COL_NUMBER][arr[ZERO] % ROW_COL_NUMBER] ==
                board[arr[TWO] / ROW_COL_NUMBER][arr[TWO] % ROW_COL_NUMBER] &&
                board[arr[ZERO] / ROW_COL_NUMBER][arr[ZERO] % ROW_COL_NUMBER] != BoxState.Empty
            ) {
                if (board[arr[ZERO] / ROW_COL_NUMBER][arr[ZERO] % ROW_COL_NUMBER] == BoxState.O) {
                    return MAX_SCORE
                } else {
                    return MIN_SCORE
                }
            }
        }
        return 0;
    }
    fun minimax(
        board:Array<Array<BoxState>>,
        depth: Int,
        isAI: Boolean
    ): Int{
        val score = evaluate(board)

        return when (score){
            MAX_SCORE -> MAX_SCORE
            MIN_SCORE -> MIN_SCORE
            DRAW -> if (!isMovesLeft(board)){
                DRAW
            }else{
                if(isAI){
                    var best = -1000
                    for (i in 0..2) {
                        for (j in 0..2) {
                            // Check if cell is empty
                            if (board[i][j] == BoxState.Empty) {
                                // Make the move
                                board[i][j] = BoxState.O

                                // Call minimax recursively and choose
                                // the maximum value
                                best = best.coerceAtLeast(
                                    minimax(
                                        board,
                                        depth + 1, !isAI
                                    )
                                )
                                // Undo the move
                                board[i][j] = BoxState.Empty
                            }
                        }
                    }
                    best
                }else{
                    var best = 1000
                    for (i in 0..2) {
                        for (j in 0..2) {
                            // Check if cell is empty
                            if (board[i][j] == BoxState.Empty) {
                                // Make the move
                                board[i][j] = BoxState.X

                                // Call minimax recursively and choose
                                // the minimum value
                                best = best.coerceAtMost(
                                    minimax(
                                        board,
                                        depth + 1, !isAI
                                    )
                                )
                                // Undo the move
                                board[i][j] = BoxState.Empty
                            }
                        }
                    }
                    best
                }
            }
            else -> DRAW
        }
    }

    fun findBestMove(board: Array<Array<BoxState>>): Move {
        var bestVal = -1000
        val bestMove = Move()
        bestMove.row = -1
        bestMove.col = -1
        val moves = mutableListOf<Move>()


        Timber.e("${board[0][0]} ${board[0][1]} ${board[0][2]}")
        Timber.e("${board[1][0]} ${board[1][1]} ${board[1][2]}")
        Timber.e("${board[2][0]} ${board[2][1]} ${board[2][2]}")
        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (i in 0..2) {
            for (j in 0..2) {
                // Check if cell is empty
                if (board[i][j] == BoxState.Empty) {
                    // Make the move
                    board[i][j] = BoxState.O

                    // compute evaluation function for this
                    // move.
                    val moveVal = minimax(board, 0, false)

                    // Undo the move
                    board[i][j] = BoxState.Empty
                    val move = Move()
                    move.row = i
                    move.col = j
                    move.score = moveVal
                    moves.add(move)

                    // If the value of the current move is
                    // more than the best value, then update
                    // best
                    if (moveVal > bestVal) {
                        bestMove.row = i
                        bestMove.col = j
                        bestVal = moveVal
                    }
                }
            }
        }

        val m = moves.sortBy { move ->
            move.score
        }

        var move = Move()
        move.score = -1000
        moves.forEach {
            if(it.score > move.score){
                move = it
            }else if(move.score == it.score){
                move = listOf(it, move)[nextInt(0,2)]
            }
        }
        return move
    }

    fun getBoard(): Array<Array<BoxState>> {
        val board = arrayOf(
            arrayOf<BoxState>(BoxState.Empty, BoxState.Empty, BoxState.Empty),
            arrayOf<BoxState>(BoxState.Empty, BoxState.Empty, BoxState.Empty),
            arrayOf<BoxState>(BoxState.Empty, BoxState.Empty, BoxState.Empty)
        )
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = game[i][j].value!!
            }
        }
        return board
    }

    companion object {
        private const val ZERO = 0
        private const val ONE = 1
        private const val TWO = 2
        private const val THREE = 3
        private const val FOUR = 4
        private const val FIVE = 5
        private const val SIX = 6
        private const val SEVEN = 7
        private const val EIGHT = 8
        private const val MAX_SCORE = 10
        private const val MIN_SCORE = -10
        private const val DRAW = 0

        private const val ROW_COL_NUMBER = 3
        private const val INCREMENT_NUM = 1

        private const val TOTAL_NUMBER_OF_BOXES = 9
    }
}


