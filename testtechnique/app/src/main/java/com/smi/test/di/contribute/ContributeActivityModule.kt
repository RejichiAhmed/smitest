package com.smi.test.di.contribute

import com.smi.test.di.ActivityScope
import com.smi.test.ui.auth.SignInActivity
import com.smi.test.ui.auth.SignInModelModule
import com.smi.test.ui.brand.BrandActivity
import com.smi.test.ui.brand.BrandModule
import com.smi.test.ui.home.HomeActivity
import com.smi.test.ui.home.HomeModule
import com.smi.test.ui.signup.SignUpActivity
import com.smi.test.ui.signup.SignUpModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SignInModelModule::class])
    abstract fun contributeSignInActivity(): SignInActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SignUpModelModule::class])
    abstract fun contributeSignUPActivity(): SignUpActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeModule::class, ContributeFragmentModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [BrandModule::class])
    abstract fun contributeBrandActivity(): BrandActivity
    

}
