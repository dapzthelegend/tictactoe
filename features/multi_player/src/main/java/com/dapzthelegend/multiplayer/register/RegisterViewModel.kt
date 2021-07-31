package com.dapzthelegend.multiplayer.register

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import com.dapzthelegend.ui.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel: BaseViewModel<RegisterViewEvent>() {

    val _playerName1 = MutableStateFlow<String>("")
    val playerName1: StateFlow<String>
        get() = _playerName1

    val _playerName2 = MutableStateFlow<String>("")
    val playerName2: StateFlow<String>
        get() = _playerName2

    val inputState: Flow<Boolean> = combine(playerName1, playerName2){ name1, name2 ->
        !TextUtils.isEmpty(name1)  || !TextUtils.isEmpty(name1)
    }

    lateinit var state: StateFlow<RegisterViewState>


}
