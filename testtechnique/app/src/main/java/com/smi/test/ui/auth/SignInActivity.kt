package com.smi.test.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.viewModels
import androidx.databinding.*
import com.google.firebase.auth.FirebaseAuth
import com.smi.test.R
import com.smi.test.base.BaseActivity
import com.smi.test.data.model.User
import com.smi.test.databinding.ActivitySigninBinding
import com.smi.test.global.helper.Navigation
import com.smi.test.global.helper.ViewModelFactory
import com.smi.test.global.utils.DebugLog
import com.smi.test.global.utils.TAG
import com.smi.test.ui.home.HomeActivity
import com.smi.test.ui.signup.SignUpActivity
import javax.inject.Inject

class SignInActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SignInViewModel  by viewModels { viewModelFactory }
    lateinit var binding : ActivitySigninBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
        auth = FirebaseAuth.getInstance()
        registerBindingAndBaseObservers(binding)
        registerSinInObservers()
    }

    private fun registerSinInObservers() {
        viewModel.showPassword.observe(this) { showPassword(it) }

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
            is Navigation.SignUpActivityNavigation -> navigateToActivity(SignUpActivity::class, true)
            is Navigation.Back -> finish()
        }
    }

    /**
     * update password visibility state
     */
    private fun showPassword(show: Boolean) {
        if (show) {
            binding.editSigninPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            binding.editSigninPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }



    private fun registerBindingAndBaseObservers(binding: ActivitySigninBinding) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        registerBaseObservers(viewModel)
    }

}
