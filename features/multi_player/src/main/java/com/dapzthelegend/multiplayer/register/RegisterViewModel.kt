package com.dapzthelegend.multiplayer.register

import android.text.TextUtils
import com.dapzthelegend.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

/**
 * View model that prepares data and manages the state of [RegisterFragment]
 *
 * @see BaseViewModel
 */
class RegisterViewModel : BaseViewModel<RegisterViewEvent>() {

    private val _playerName1 = MutableStateFlow<String>("")
    val playerName1: StateFlow<String>
        get() = _playerName1

    private val _playerName2 = MutableStateFlow<String>("")
    val playerName2: StateFlow<String>
        get() = _playerName2

    val inputState: Flow<Boolean> = combine(playerName1, playerName2) { name1, name2 ->
        !TextUtils.isEmpty(name1) || !TextUtils.isEmpty(name1)
    }

    lateinit var state: StateFlow<RegisterViewState>
}
