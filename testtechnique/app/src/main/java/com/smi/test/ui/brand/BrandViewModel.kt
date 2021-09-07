package com.smi.test.ui.brand

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smi.test.Application
import com.smi.test.base.BaseAndroidViewModel
import com.smi.test.data.model.Brands
import com.smi.test.data.model.Statistic
import com.smi.test.data.repository.abs.BrandsRepository
import com.smi.test.global.helper.NonNullLiveData
import com.smi.test.global.listener.SchedulerProvider
import com.smi.test.global.utils.ExtraKeys
import com.smi.test.global.utils.tryCatch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class BrandViewModel @Inject
constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    private val brandsRepository: BrandsRepository,
    @Named(ExtraKeys.HomeActivity.BRAND_INJECT_VALUE_KEY) val brands: Brands
) :
    BaseAndroidViewModel(application, schedulerProvider) {

    val purchase : MutableLiveData<Statistic> = MutableLiveData()
    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)
    val commission: NonNullLiveData<String> = NonNullLiveData("Commission : ")
    val amount: NonNullLiveData<String> = NonNullLiveData("Amount : ")
    val count: NonNullLiveData<String> = NonNullLiveData("Count : ")

    init {
        viewModelScope.launch {
            isLoading.value = true
            showBlockProgressBar()
            tryCatch({
                purchase.value = withContext(schedulerProvider.dispatchersIO()) {
                    brandsRepository.getPurchase(brands.offerId!!)
                }
                isLoading.value = false
                hideBlockProgressBar()
            }, {
                isLoading.value = false
                hideBlockProgressBar()
            })
        }
    }

}
