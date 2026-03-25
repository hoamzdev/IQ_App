package com.hoamz.iq.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

/**
 * @author hwa..
 */

@Composable
private fun AppLifeCycle(
    onEvent :(Lifecycle.Event) -> Unit
){
    val lifeCycleOwner = LocalLifecycleOwner.current
    val currentEventState by rememberUpdatedState(onEvent)
    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver{_,event ->
            currentEventState(event)
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun Pause(action :() -> Unit){
   AppLifeCycle {event ->
       if(event == Lifecycle.Event.ON_PAUSE){
           action()
       }
   }
}

@Composable
fun Stop(action :() -> Unit){
    AppLifeCycle {event ->
        if(event == Lifecycle.Event.ON_STOP){
            action()
        }
    }
}

@Composable
fun Destroy(action :() -> Unit){
    AppLifeCycle {event ->
        if(event == Lifecycle.Event.ON_DESTROY){
            action()
        }
    }
}