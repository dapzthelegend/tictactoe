package com.dapzthelegend.game.xo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dapzthelegend.game.xo.di.DaggerXOComponent
import com.dapzthelegend.game.xo.di.XOModule
import com.dapzthelegend.game.xo.game.Player
import com.dapzthelegend.multiplayer.R
import com.dapzthelegend.multiplayer.databinding.FragmentXOBinding
import com.dapzthelegend.ui.base.BaseFragment
import com.dapzthelegend.ui.extensions.collect
import com.dapzthelegend.ui.extensions.observe
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * User interface view for managing XO multi player game.
 */
class XOFragment : BaseFragment<FragmentXOBinding, XOViewModel>(
    layoutId = R.layout.fragment_x_o
) {

    private val args: XOFragmentArgs by navArgs()

    companion object {
        const val START_GAME_DELAY = 200L
        const val MID_GAME_DELAY = 500L
        const val MAX_ROUNDS = 9
        const val MIN_ROUNDS = 0
    }

    /**
     * Called to have the fragment instantiate the user interface view
     *
     * @param view The view returned by [BaseFragment.onCreateView]
     * @param savedInstanceState If non-null, this fragment is being reconstructed from a previous
     * state
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collect(viewModel.event, lifecycle, ::onViewEventChanged)
        observe(viewModel.gameManager.player, ::onPlayerChanged)
    }

    /**
     * Instantiate dagger XO component object graph.
     */
    override fun onInitDependencyInjection() {
        DaggerXOComponent
            .builder()
            .xOModule(XOModule(this))
            .build()
            .inject(this)
    }

    /**
     * Instantiate view binding with data variables.
     */
    override fun onInitDataBinding() {
        viewModel.x = args.playerName1
        viewModel.o = args.playerName2
        viewModel.isMultiPlayer = args.isMultiPlayer
        viewBinding.viewModel = viewModel
    }

    // =============================================================================================
    // Private collector
    // =============================================================================================

    /**
     * Collector for view event.
     */
    private fun onViewEventChanged(viewEvent: XOViewEvent) {
        when (viewEvent) {
            is XOViewEvent.ReturnToHome ->
                findNavController()
                    .popBackStack()
        }
    }

    // =============================================================================================
    // Private observer
    // =============================================================================================

    /**
     * Observer for play changed.
     */
    private fun onPlayerChanged(player: Player) {
        fun isAITurn() = !args.isMultiPlayer && player == Player.PLAYER_2
        if (isAITurn() && viewModel.gameManager.round != MAX_ROUNDS) {
            lifecycleScope.launch {
                if (viewModel.gameManager.round != MIN_ROUNDS) {
                    delay(MID_GAME_DELAY)
                } else {
                    delay(START_GAME_DELAY)
                }
                val bestMove = viewModel.gameManager.findBestMove(viewModel.gameManager.getBoard())
                viewModel.makeMove(bestMove.row, bestMove.col)
            }
        }
    }
}
