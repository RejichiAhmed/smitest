package com.smi.test.ui.auth

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.smi.test.Application
import com.smi.test.R
import com.smi.test.base.BaseAndroidViewModel
import com.smi.test.data.model.User
import com.smi.test.data.repository.abs.UserRepository
import com.smi.test.global.helper.Navigation
import com.smi.test.global.helper.NonNullLiveData
import com.smi.test.global.listener.SchedulerProvider
import com.smi.test.global.listener.ToolBarListener
import com.smi.test.global.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject


class SignInViewModel @Inject
constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    private val userRepository: UserRepository
) :
    BaseAndroidViewModel(application, schedulerProvider), ToolBarListener {


    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()


    val showPassword: NonNullLiveData<Boolean> = NonNullLiveData(false)
    val signingIn: NonNullLiveData<Boolean> = NonNullLiveData(false)


    init {
        viewModelScope.launch {
            signingIn.value =true
            tryCatch({
               val user = withContext(schedulerProvider.dispatchersIO()) {
                    userRepository.getUser()
                }
                if(user != null ){
                    navigate(Navigation.HomeActivityNavigation(User(user.uid,user.displayName,user.email)))
                    signingIn.value =false
                }else{
                    signingIn.value =false
                }
            }, {
                signingIn.value =false
            })
        }
    }

    fun onSignUpClicked() {
        navigate(Navigation.SignUpActivityNavigation)
    }

    override fun onStartClicked() {
        navigate(Navigation.Back)
    }

    fun onShowPasswordClicked(show: Boolean) {
        showPassword.value = show
    }


    fun onSignInClicked() {
        if (validateFields()) {
            signingIn.value =true
                viewModelScope.launch {
                tryCatch({
                    val response = withContext(schedulerProvider.dispatchersIO()) {
                        userRepository.signIn(email.value!!,password.value!!)
                    }
                    onSignInSuccess(response)
                }, {
                    onSignInError(it)
                })
            }
        }
    }


    fun onSignInSuccess(userResponse: FirebaseUser) {
        signingIn.value = false
        navigate(Navigation.HomeActivityNavigation(User(userResponse.uid,userResponse.displayName,userResponse.email)))
    }

    fun onSignInError(throwable: Throwable) {
        signingIn.value = false
        if (throwable is HttpException) {
            when (throwable.code()) {
                HttpResponseCode.HTTP_UNAUTHORIZED -> shownSimpleDialog(messageId = R.string.global_error_server)
                HttpResponseCode.HTTP_BAD_REQUEST -> shownSimpleDialog(messageId = R.string.global_error_banned_user)
                else -> handleThrowable(throwable)
            }
        } else {
            handleThrowable(throwable)
        }
    }


    /**
     * Check inputs validity
     */
    private fun validateFields(): Boolean {
        if (TextUtils.isEmpty(email.value) || TextUtils.isEmpty(password.value)) {
            shownSimpleDialog(messageId = R.string.global_error_empty)
        } else if (!email.value.isValidEmail()) {
            shownSimpleDialog(messageId = R.string.global_error_invalid_email_format)
        } else if (password.value!!.length < MIN_PASSWORD_SIZE) {
            shownSimpleDialog(messageId = R.string.global_error_password_length)
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
