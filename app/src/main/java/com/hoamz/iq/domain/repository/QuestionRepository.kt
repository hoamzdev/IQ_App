package com.hoamz.iq.domain.repository

import com.hoamz.iq.domain.entities.Question
import com.hoamz.iq.domain.common.Result

/**
 * @author hwa..
 */
interface QuestionRepository {
    suspend fun getQuestions() : Result<List<Question>>
    fun saveLevelCurrent(level : Int)
    fun getLevelCurrent() : Int
}