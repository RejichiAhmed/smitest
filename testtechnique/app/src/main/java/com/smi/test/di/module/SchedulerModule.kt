package com.smi.test.di.module

import com.smi.test.global.helper.AppSchedulerProvider
import com.smi.test.global.listener.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulerModule {

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}
