package com.smi.test.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.smi.test.R
import com.smi.test.base.BaseActivity
import com.smi.test.data.model.User
import com.smi.test.databinding.ActivitySignupBinding
import com.smi.test.global.helper.Navigation
import com.smi.test.global.helper.ViewModelFactory
import com.smi.test.global.utils.DebugLog
import com.smi.test.global.utils.TAG
import com.smi.test.ui.auth.SignInActivity
import com.smi.test.ui.home.HomeActivity
import javax.inject.Inject

class SignUpActivity : BaseActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SignUpViewModel by viewModels { viewModelFactory }

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySignupBinding>(this, R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        registerBindingAndBaseObservers(binding)

    }



    private fun navigateToHome(user: User) {
           Intent(this, HomeActivity::class.java).let {
           startActivity(it)
           finish()
       }
    }


    /**
     * handling navigation event
     */
    override fun navigate(navigationTo: Navigation) {
        when (navigationTo) {
            is Navigation.HomeActivityNavigation -> {
                DebugLog.d(TAG, "User is loggedIn value is ${navigationTo.user}")
                navigateToHome(navigationTo.user)
            }
            is Navigation.Back -> finish()
            is Navigation.SignInActivityNavigation -> navigateToActivity(SignInActivity::class, true)
        }
    }


    /**
     * Register the UI for XMLBinding
     * Register the activity for base observers
     */
    private fun registerBindingAndBaseObservers(binding: ActivitySignupBinding) {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        registerBaseObservers(viewModel)
    }
}
