package com.hoamz.iq.presentation.di

import com.hoamz.iq.data.repository.QuestionRepositoryImpl
import com.hoamz.iq.domain.repository.QuestionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author hwa..
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun questionProvide(
        questionRepositoryImpl: QuestionRepositoryImpl
    ) : QuestionRepository
}