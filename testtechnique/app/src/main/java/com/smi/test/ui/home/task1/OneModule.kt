package com.smi.test.ui.home.task1

import androidx.lifecycle.ViewModel
import com.smi.test.di.FragmentScope
import com.smi.test.di.ViewModelKey
import com.smi.test.di.module.RepositoryModule
import com.smi.test.di.module.SchedulerModule
import com.smi.test.ui.adapter.BrandsAdapter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [RepositoryModule::class, SchedulerModule::class])
class OneModule {


    @IntoMap
    @Provides
    @ViewModelKey(OneViewModel::class)
    fun bindOneViewModel(viewModel: OneViewModel): ViewModel {
        return viewModel
    }


    @Provides
    @FragmentScope
    fun provideBrandsCachedAdapter(picasso: Picasso): BrandsAdapter {
        return BrandsAdapter(picasso)
    }
}
