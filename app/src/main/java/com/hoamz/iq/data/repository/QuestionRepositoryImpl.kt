package com.hoamz.iq.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.media3.common.util.Log
import com.hoamz.iq.data.common.Constant
import com.hoamz.iq.data.db.AssetsName
import com.hoamz.iq.domain.entities.Question
import com.hoamz.iq.domain.common.DispatchersProvide
import com.hoamz.iq.domain.common.Result
import com.hoamz.iq.domain.repository.QuestionRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

/**
 * @author hwa..
 */
class QuestionRepositoryImpl @Inject constructor(
    private val dispatchersProvide: DispatchersProvide,
    @param:ApplicationContext private val context: Context,
    private val sharedPref: SharedPreferences
) : QuestionRepository {

    //get json -> text
    private suspend fun getDataJsonToText() : String{
        return withContext(dispatchersProvide.io){
            context.assets.open(AssetsName.namePart1)
                .bufferedReader().use { it.readText() }
        }
    }

    //text json -> list<Question>
    override suspend fun getQuestions(): Result<List<Question>> {
        // TODO: 25/3/26
        return withContext(dispatchersProvide.io){
            try{
                val json = getDataJsonToText()
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val type = Types.newParameterizedType(
                    List::class.java, Question::class.java
                )
                val adapter = moshi.adapter<List<Question>>(type)
                val questions : List<Question> = adapter.fromJson(json)?: emptyList()
                Log.e("IQ",questions.size.toString())
                Result.Success(questions)
            }catch (e : Exception){
                Log.e("IQ",e.message.toString())
                Result.Error(Throwable("Something wrong, please try again"))
            }
        }
    }

    override fun saveLevelCurrent(level: Int){
        sharedPref.edit {
            putInt(Constant.currentLevelKey,level)
        }
    }

    override fun getLevelCurrent(): Int {
        return sharedPref.getInt(Constant.currentLevelKey,0)
    }
}