package com.smi.test.di.module

import android.content.Context
import com.smi.test.Application
import com.smi.test.di.ApplicationContext
import com.smi.test.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [APIClientModule::class, PicassoModule::class, PreferencesModule::class])
class ApplicationModule() {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun context(application: Application): Context {
        return application.applicationContext
    }
}
