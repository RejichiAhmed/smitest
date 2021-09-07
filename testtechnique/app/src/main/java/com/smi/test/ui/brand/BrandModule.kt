package com.smi.test.ui.brand

import androidx.lifecycle.ViewModel
import com.smi.test.data.model.Brands
import com.smi.test.di.ViewModelKey
import com.smi.test.di.module.RepositoryModule
import com.smi.test.di.module.SchedulerModule
import com.smi.test.global.utils.ExtraKeys
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named


@Module(includes = [RepositoryModule::class, SchedulerModule::class])
class BrandModule {


    @IntoMap
    @Provides
    @ViewModelKey(BrandViewModel::class)
    fun bindBrandViewModel(brandViewModel: BrandViewModel): ViewModel {
        return brandViewModel
    }

    @Provides
    @Named(ExtraKeys.HomeActivity.BRAND_INJECT_VALUE_KEY)
    fun provideBrand(brandActivity: BrandActivity): Brands {
        return brandActivity.intent.getParcelableExtra(ExtraKeys.HomeActivity.BRAND_EXTRA_VALUE_KEY)!!
    }


}
