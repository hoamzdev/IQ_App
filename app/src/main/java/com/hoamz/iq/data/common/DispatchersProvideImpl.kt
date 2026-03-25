package com.hoamz.iq.data.common

import com.hoamz.iq.domain.common.DispatchersProvide
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

/**
 * @author hwa..
 */
data class DispatchersProvideImpl(
    override val io: CoroutineDispatcher,
    override val main: MainCoroutineDispatcher,
    override val default: CoroutineDispatcher
) : DispatchersProvide