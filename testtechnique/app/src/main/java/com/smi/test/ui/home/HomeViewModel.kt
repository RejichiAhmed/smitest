package com.smi.test.ui.home

import com.google.firebase.auth.FirebaseAuth
import com.smi.test.Application
import com.smi.test.base.BaseAndroidViewModel
import com.smi.test.global.helper.Navigation
import com.smi.test.global.listener.SchedulerProvider
import javax.inject.Inject

class HomeViewModel @Inject
constructor(
    application: Application,
    schedulerProvider: SchedulerProvider
) :
    BaseAndroidViewModel(application, schedulerProvider) {

    init {
    }

    fun onLogoutClicked(){
        FirebaseAuth.getInstance().signOut()
        navigate(Navigation.SignInActivityNavigation)
    }
}
