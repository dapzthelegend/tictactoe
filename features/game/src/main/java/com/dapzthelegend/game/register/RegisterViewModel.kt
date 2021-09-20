package com.dapzthelegend.game.register

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import com.dapzthelegend.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * View model that prepares data and manages the state of [RegisterFragment]
 *
 * @see BaseViewModel
 */
class RegisterViewModel : BaseViewModel<RegisterViewEvent>() {

    val playerName1 = MutableStateFlow("")
    val playerName2 = MutableStateFlow("")
    var isMultiPlayer = true

    private val inputState: Flow<Boolean> = combine(playerName1, playerName2) { name1, name2 ->
        !TextUtils.isEmpty(name1) || !TextUtils.isEmpty(name2)
    }

    var state: StateFlow<RegisterViewState> = inputState.map {
        if (it) {
            RegisterViewState.NameEntered(isMultiPlayer)
        } else {
            RegisterViewState.NoNameEntered(isMultiPlayer)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = RegisterViewState.NoNameEntered(isMultiPlayer)
    )

    // =============================================================================================
    // Public methods
    // =============================================================================================

    /**
     * Called when skip or start is clicked.
     *
     * @param playerName1 The player 1 name with a default null value.
     * @param playerName2 The player 2 name with a default null value.
     */
    fun onSkipOrStartClicked(playerName1: String? = null, playerName2: String? = null) {
        viewModelScope.launch {
            event.emit(RegisterViewEvent.OpenXO(playerName1, playerName2))
        }
    }

    /**
     * Called when home is clicked.
     */
    fun onHomeClicked() {
        viewModelScope.launch {
            event.emit(RegisterViewEvent.ReturnToHome)
        }
    }
}
