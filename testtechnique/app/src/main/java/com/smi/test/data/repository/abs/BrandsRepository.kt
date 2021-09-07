package com.smi.test.data.repository.abs

import androidx.annotation.WorkerThread
import com.smi.test.data.model.Brands
import com.smi.test.data.model.Statistic

interface BrandsRepository {

    @WorkerThread
    suspend fun getAllBrands(): List<Brands>?

    @WorkerThread
    suspend fun getPremiumBrands(): List<Brands>?

    @WorkerThread
    suspend fun getPurchase(id: Long): Statistic


}