package com.hoamz.iq.presentation.ui.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author hwa..
 */

@HiltViewModel
class WelcomeViewModel @Inject constructor(

) : ViewModel() {
    private val _welcomeNavState = MutableSharedFlow<WelcomeNavState>()
    val welcomeNavState = _welcomeNavState.asSharedFlow()

    //fun onNav
    fun onNavToPlayScreen(){
        viewModelScope.launch {
            _welcomeNavState.emit(WelcomeNavState.ToPlayScreen)
        }
    }
}