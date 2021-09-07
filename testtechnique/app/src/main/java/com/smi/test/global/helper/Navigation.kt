package com.smi.test.global.helper

import com.smi.test.data.model.Brands
import com.smi.test.data.model.User


sealed class Navigation {

    object Back : Navigation()

    object SignInActivityNavigation : Navigation()

    object SignUpActivityNavigation : Navigation()

    data class HomeActivityNavigation (val user: User) : Navigation()

    data class BrandActivityNavigation (val brand: Brands) : Navigation()


}