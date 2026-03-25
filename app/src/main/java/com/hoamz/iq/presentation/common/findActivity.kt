package com.hoamz.iq.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * @author hwa..
 */

fun Context.findActivity() : Activity?{
    return when(this){
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}