package com.hoamz.iq.presentation.common

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import com.hoamz.iq.R
import java.lang.Exception

/**
 * @author hwa..
 */

fun playAudioCorrectAnswer(
    context: Context
) {
    val media: MediaPlayer? = MediaPlayer.create(context, R.raw.correct_sound)
    media?.start()
    media?.setOnCompletionListener {
        it.release()
    }
}
