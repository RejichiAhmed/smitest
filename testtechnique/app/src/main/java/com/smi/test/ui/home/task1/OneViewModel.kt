package com.smi.test.ui.home.task1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smi.test.Application
import com.smi.test.R
import com.smi.test.base.BaseAndroidViewModel
import com.smi.test.data.model.Brands
import com.smi.test.data.repository.abs.BrandsRepository
import com.smi.test.global.helper.Navigation
import com.smi.test.global.helper.NonNullLiveData
import com.smi.test.global.listener.OnItemClickedListener
import com.smi.test.global.listener.SchedulerProvider
import com.smi.test.global.utils.HttpResponseCode
import com.smi.test.global.utils.tryCatch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class OneViewModel @Inject
constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    brandsRepository: BrandsRepository

) : BaseAndroidViewModel(application, schedulerProvider) , OnItemClickedListener {


    //using live data to keep list up to date
    val brands = MutableLiveData<List<Brands>>()


    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)

    init {
        isLoading.value = true
        viewModelScope.launch {
            tryCatch({
                brands.value = withContext(schedulerProvider.dispatchersIO()) {
                    brandsRepository.getPremiumBrands()
                }
                isLoading.value = false
            }, {
                onFetchError(it)
            })
        }
    }

    fun onFetchError(throwable: Throwable) {
        isLoading.value = false
        if (throwable is HttpException) {
           handleThrowable(throwable)
        } else {
            handleThrowable(throwable)
        }
    }

    override fun onItemClicked(value: Brands) {
        navigate(Navigation.BrandActivityNavigation(value))
    }

}
