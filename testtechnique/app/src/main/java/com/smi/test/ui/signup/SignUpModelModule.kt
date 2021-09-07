package com.smi.test.ui.signup


import androidx.lifecycle.ViewModel
import com.smi.test.di.ViewModelKey
import com.smi.test.di.module.RepositoryModule
import com.smi.test.di.module.SchedulerModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [RepositoryModule::class, SchedulerModule::class])
abstract class SignUpModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSinUpViewModel(viewModel: SignUpViewModel): ViewModel

}
