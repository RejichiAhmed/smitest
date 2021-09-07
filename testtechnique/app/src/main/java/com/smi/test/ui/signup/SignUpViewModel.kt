package com.smi.test.ui.signup

import android.text.*
import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.smi.test.Application
import com.smi.test.R
import com.smi.test.base.BaseAndroidViewModel
import com.smi.test.data.model.User
import com.smi.test.data.repository.abs.UserRepository
import com.smi.test.global.helper.Navigation
import com.smi.test.global.listener.SchedulerProvider
import com.smi.test.global.listener.ToolBarListener
import com.smi.test.global.utils.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject


class SignUpViewModel @Inject
constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    private val userRepository: UserRepository
) : BaseAndroidViewModel(application, schedulerProvider), ToolBarListener {

    val name: MutableLiveData<String> = MutableLiveData()
    val lastName: MutableLiveData<String> = MutableLiveData()

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()


    init {

    }

    override fun onStartClicked() {
        navigate(Navigation.Back)
    }

    @UiThread
    fun onSignUpClicked() {
        if (validateFields()) {
            showBlockProgressBar()
            viewModelScope.launch {
                tryCatch({
                    val response = withContext(schedulerProvider.dispatchersIO()) {
                        userRepository.signUp(name.value!!,password.value!!,email.value!!,password.value!!)
                    }
                    onSignUpSuccess(response)
                }, {
                    onSignInError(it)
                })
            }
        }
    }


    fun onSignUpSuccess(userResponse: FirebaseUser?) {
        hideBlockProgressBar()
        navigate(Navigation.HomeActivityNavigation(User(userResponse!!.uid,userResponse.displayName,userResponse.email)))
    }


    fun onSignInError(throwable: Throwable) {
        hideBlockProgressBar()
        if (throwable is HttpException) {
            when (throwable.code()) {
                HttpResponseCode.HTTP_PAYMENT_REQUIRED -> shownSimpleDialog(messageId = R.string.global_error_banned_user)
                else -> handleThrowable(throwable)
            }
        } else {
            handleThrowable(throwable)
        }
    }


    fun onSignInClicked() {
        navigate(Navigation.SignInActivityNavigation)
    }

    private fun validateFields(): Boolean {
        if (TextUtils.isEmpty(name.value) || name.value!!.matches(WHITE_SPACE.toRegex()) || TextUtils.isEmpty(lastName.value) || lastName.value!!.matches(
                WHITE_SPACE.toRegex()
            ) || TextUtils.isEmpty(email.value) || TextUtils.isEmpty(password.value)
        ) {
            shownSimpleDialog(messageId = R.string.global_error_empty)
        } else if (!email.value.isValidEmail()) {
            shownSimpleDialog(messageId = R.string.global_error_invalid_email_format)
        } else if (password.value!!.length < MIN_PASSWORD_SIZE) {
            shownSimpleDialog(messageId = R.string.global_error_password_length)
        } else if (name.value!!.length > MAX_PASSWORD_SIZE) {
            shownSimpleDialog(messageId = R.string.global_error_first_length)
        } else if (lastName.value!!.length > MAX_PASSWORD_SIZE) {
            shownSimpleDialog(messageId = R.string.global_error_last_length)
        } else if (password.value!!.length > MAX_PASSWORD_SIZE) {
            shownSimpleDialog(messageId = R.string.global_error_password_length_max)
        } else if (email.value!!.length > MAX_EMAIL_SIZE) {
            shownSimpleDialog(messageId = R.string.global_error_mail_length_max)
        } else {
            return true
        }
        return false
    }

}
