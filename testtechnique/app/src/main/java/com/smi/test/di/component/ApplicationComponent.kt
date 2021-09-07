package com.smi.test.di.component


import com.smi.test.Application
import com.smi.test.di.ApplicationScope
import com.smi.test.di.contribute.ContributeActivityModule
import com.smi.test.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@ApplicationScope
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, ContributeActivityModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance

        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: Application)

}
