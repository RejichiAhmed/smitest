package com.smi.test.ui.auth


import androidx.lifecycle.ViewModel
import com.smi.test.di.ViewModelKey
import com.smi.test.di.module.RepositoryModule
import com.smi.test.di.module.SchedulerModule
import com.smi.test.ui.auth.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [RepositoryModule::class, SchedulerModule::class])
abstract class SignInModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel
}
