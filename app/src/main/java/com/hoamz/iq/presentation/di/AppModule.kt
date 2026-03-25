package com.hoamz.iq.presentation.di

import android.content.Context
import android.content.SharedPreferences
import com.hoamz.iq.data.common.DispatchersProvideImpl
import com.hoamz.iq.domain.common.DispatchersProvide
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * @author hwa..
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @IODispatcher
    fun ioDispatcherProvide() : CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun mainDispatcherProvide() : MainCoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatcher
    fun defaultDispatcherProvide() : CoroutineDispatcher = Dispatchers.Default


    @Provides
    @Singleton
    fun dispatcherProvide(
        @IODispatcher ioDispatcher: CoroutineDispatcher,
        @MainDispatcher mainDispatcher: MainCoroutineDispatcher,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ) : DispatchersProvide {
        return DispatchersProvideImpl(
            io = ioDispatcher,
            main = mainDispatcher,
            default = defaultDispatcher
        )
    }

    @Provides
    @Singleton
    fun sharePrefProvide(
        @ApplicationContext context: Context
    ) : SharedPreferences{
        return context.getSharedPreferences("IQ_Viettel", Context.MODE_PRIVATE)
    }
}