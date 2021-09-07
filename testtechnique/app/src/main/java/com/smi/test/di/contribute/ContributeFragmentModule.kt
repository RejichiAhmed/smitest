package com.smi.test.di.contribute


import com.mvvm.ui.home.task2.TwoModule
import com.smi.test.di.FragmentScope
import com.smi.test.ui.home.task1.OneFragment
import com.smi.test.ui.home.task1.OneModule
import com.smi.test.ui.home.task2.TwoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [OneModule::class])
    abstract fun contributeOneFragment(): OneFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [TwoModule::class])
    abstract fun contributeTwoFragment(): TwoFragment
}