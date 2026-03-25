package com.hoamz.iq.presentation.ui.widget

import android.content.Context
import android.widget.Toast

/**
 * @author hwa..
 */

fun toast(context: Context,message : String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}