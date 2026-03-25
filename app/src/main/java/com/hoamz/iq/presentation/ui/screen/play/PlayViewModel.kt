package com.hoamz.iq.presentation.ui.screen.play

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import com.hoamz.iq.domain.common.onError
import com.hoamz.iq.domain.common.onSuccess
import com.hoamz.iq.domain.usecase.GetLevelCurrent
import com.hoamz.iq.domain.usecase.GetQuestions
import com.hoamz.iq.domain.usecase.SaveLevelCurrent
import com.hoamz.iq.presentation.stateData.QuestionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author hwa..
 */
@HiltViewModel
class PlayViewModel @Inject constructor(
    private val getQuestions: GetQuestions,
    private val saveLevelCurrent: SaveLevelCurrent,
    private val getLevelCurrent: GetLevelCurrent,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    //get currentLevel
    val levelSelected : Int = savedStateHandle["level"] ?: -1

    private val _playNavState = MutableSharedFlow<PlayNavState>()
    val playNavState = _playNavState.asSharedFlow()

    fun onNavToLevelsScreen(){
        viewModelScope.launch {
            _playNavState.emit(PlayNavState.ToLevelScreen)
        }
    }

    private val _errorState = MutableSharedFlow<String>()
    val errorState = _errorState.asSharedFlow()

    private val _questionData = MutableStateFlow(QuestionData())
    val questions = _questionData.asStateFlow()

    init {
        freshQuestion()
    }

    private fun freshQuestion(){
        viewModelScope.launch {
            getQuestions()
                .onSuccess { questions ->
                    _questionData.update {
                        it.copy(questions = questions)
                    }
                    Log.e("IQ",_questionData.value.questions.size.toString())
                }
                .onError {e->
                    _errorState.emit(e.message.toString())
                }
        }
    }

    fun saveLevel(level : Int){
        saveLevelCurrent(level)
    }

    fun getLevel() = getLevelCurrent()
}