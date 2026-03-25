package com.hoamz.iq.domain.usecase

import com.hoamz.iq.domain.repository.QuestionRepository
import javax.inject.Inject

/**
 * @author hwa..
 */
class SaveLevelCurrent @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(level : Int) = questionRepository.saveLevelCurrent(level)
}