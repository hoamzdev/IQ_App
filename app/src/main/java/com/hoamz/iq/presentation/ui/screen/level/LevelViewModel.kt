package com.hoamz.iq.presentation.ui.screen.level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoamz.iq.domain.usecase.GetLevelCurrent
import com.hoamz.iq.presentation.ui.screen.play.PlayNavState
import com.hoamz.iq.ui.screen.level.LevelNavState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author hwa..
 */
@HiltViewModel
class LevelViewModel @Inject constructor(
    private val getLevelCurrent: GetLevelCurrent
) : ViewModel() {
    private val _levelsNavState = MutableSharedFlow<LevelNavState>()
    val levelsNavState = _levelsNavState.asSharedFlow()

    fun onNavToWelcomeScreen(){
        viewModelScope.launch {
            _levelsNavState.emit(LevelNavState.ToWelComeScreen)
        }
    }

    fun onNavToPlayScreen(){
        viewModelScope.launch {
            _levelsNavState.emit(LevelNavState.ToPlayScreen)
        }
    }

    fun getLevel() = getLevelCurrent()
}