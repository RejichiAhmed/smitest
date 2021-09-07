package com.smi.test.di.module



import com.smi.test.data.repository.abs.BrandsRepository
import com.smi.test.data.repository.abs.UserRepository
import com.smi.test.data.repository.imp.BrandsRepositoryImp
import com.smi.test.data.repository.imp.UserRepositoryImp
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideProfileRepository(profileImp: UserRepositoryImp): UserRepository

    @Binds
    abstract fun provideBrandsRepository(brandsImp: BrandsRepositoryImp): BrandsRepository

}
