package com.hoamz.iq.presentation.state

import com.hoamz.iq.domain.entities.Question

/**
 * @author hwa..
 */
data class QuestionData(
    val questions : List<Question> = emptyList()
)