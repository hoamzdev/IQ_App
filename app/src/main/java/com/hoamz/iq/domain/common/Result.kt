package com.hoamz.iq.domain.common

/**
 * @author hwa..
 */
sealed class Result<out T>{
    data class Success<T>(val data : T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
}

inline fun <T> Result<T>.onSuccess(
    action :(T) -> Unit
) : Result<T>{
    return if(this is Result.Success){
        also { action(this.data) }
    }
    else this
}

inline fun <T> Result<T>.onError(
    action :(Throwable) -> Unit
) : Result<T>{
    return if(this is Result.Error){
        also { action(this.throwable) }
    }
    else this
}