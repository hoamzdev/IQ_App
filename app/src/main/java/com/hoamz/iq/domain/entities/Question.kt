package com.hoamz.iq.domain.entities

/**
 * @author hwa..
 */
data class Question(
    val id : Int,
    val question : String,
    val correct_answer_value : Int,
    val rule_description : String,
)