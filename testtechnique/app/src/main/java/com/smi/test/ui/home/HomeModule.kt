package com.smi.test.ui.home

import androidx.lifecycle.ViewModel
import com.smi.test.di.ViewModelKey
import com.smi.test.di.module.RepositoryModule
import com.smi.test.di.module.SchedulerModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [RepositoryModule::class, SchedulerModule::class])
class HomeModule {


    @IntoMap
    @Provides
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel {
        return homeViewModel
    }



}
