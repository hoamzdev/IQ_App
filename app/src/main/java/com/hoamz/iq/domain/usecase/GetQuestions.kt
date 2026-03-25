package com.hoamz.iq.domain.usecase

import com.hoamz.iq.domain.repository.QuestionRepository
import javax.inject.Inject

/**
 * @author hwa..
 */
class GetQuestions @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke() = questionRepository.getQuestions()
}