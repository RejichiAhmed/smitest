package com.smi.test.di.module


import android.content.Context
import com.smi.test.di.ApplicationContext
import com.smi.test.di.ApplicationScope
import com.smi.test.global.helper.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class, ParsingModule::class])
class PreferencesModule {

    @Provides
    @ApplicationScope
    fun sharedPreferences(@ApplicationContext context: Context, moshi: Moshi): SharedPreferences {
        return SharedPreferences(context, moshi)
    }
}
