package com.mvvm.ui.home.task2

import androidx.lifecycle.ViewModel
import com.smi.test.di.FragmentScope
import com.smi.test.di.ViewModelKey
import com.smi.test.di.module.RepositoryModule
import com.smi.test.di.module.SchedulerModule
import com.smi.test.ui.adapter.BrandsAdapter
import com.smi.test.ui.home.task2.TwoViewModel
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [RepositoryModule::class, SchedulerModule::class])
class TwoModule {

    @IntoMap
    @Provides
    @ViewModelKey(TwoViewModel::class)
    fun bindTwoViewModel(viewModel: TwoViewModel): ViewModel {
        return viewModel
    }

    @Provides
    @FragmentScope
    fun provideBrandsCachedAdapter(picasso: Picasso): BrandsAdapter {
        return BrandsAdapter(picasso)
    }

}
