package com.hoamz.iq.domain.usecase

import com.hoamz.iq.domain.repository.QuestionRepository
import javax.inject.Inject

/**
 * @author hwa..
 */
class GetLevelCurrent @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke() = questionRepository.getLevelCurrent()
}