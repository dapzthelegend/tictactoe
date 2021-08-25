package com.dapzthelegend.multiplayer.xo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dapzthelegend.multiplayer.R
import com.dapzthelegend.multiplayer.databinding.FragmentXOBinding
import com.dapzthelegend.multiplayer.xo.di.DaggerXOComponent
import com.dapzthelegend.multiplayer.xo.di.XOModule
import com.dapzthelegend.multiplayer.xo.game.Player
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
        viewBinding.viewModel = viewModel
    }

    // =============================================================================================
    // Private collectors
    // =============================================================================================

    /**
     * Collector for view event.
     */
    private fun onViewEventChanged(viewEvent: XOViewEvent) {
        when (viewEvent) {
            is XOViewEvent.ReturnToHome -> findNavController()
        }
    }

    /**
     * Collector for view event.
     */
    private fun onPlayerChanged(player: Player) {
        when (player) {
            Player.PLAYER_2 -> if(args.mode =="single" && viewModel.gameManager.round != 9){
                lifecycleScope.launch {
                    if(viewModel.gameManager.round != 0) delay(500)
                    else delay(100)
                    val bestMove = viewModel.gameManager.findBestMove(viewModel.gameManager.getBoard())
                    viewModel.onBoardClicked(bestMove.row, bestMove.col)
                }

            }
        }
    }
}
