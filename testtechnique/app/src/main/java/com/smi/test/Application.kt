package com.smi.test

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.smi.test.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class Application : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()

        instance = this
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    companion object {

        private lateinit var instance: Application

        fun getInstance(): Application = instance
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

}
