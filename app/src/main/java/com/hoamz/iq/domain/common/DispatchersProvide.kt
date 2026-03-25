package com.hoamz.iq.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

/**
 * @author hwa..
 */
interface DispatchersProvide {
    val io : CoroutineDispatcher
    val main : MainCoroutineDispatcher
    val default : CoroutineDispatcher
}