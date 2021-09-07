package com.smi.test.data.repository.imp


import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.smi.test.data.repository.abs.UserRepository
import com.smi.test.base.BaseRepository
import com.smi.test.data.model.User
import com.smi.test.global.helper.SharedPreferences
import com.smi.test.global.utils.TAG
import java.util.concurrent.Executor
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class UserRepositoryImp @Inject constructor(
    sharedPreferences: SharedPreferences
) :
    BaseRepository(sharedPreferences), UserRepository {

    override suspend fun signUp(name: String, lastName: String, email: String, password: String)= suspendCoroutine<FirebaseUser> { continuation ->
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email,
            password
        )
            .addOnCompleteListener(getExecutor()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user =  FirebaseAuth.getInstance().currentUser!!
                    continuation.resume(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    continuation.resumeWithException(Exception(task.exception))
                }
            }
    }

    override suspend fun signIn(email: String, password: String) = suspendCoroutine<FirebaseUser> { continuation ->

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(getExecutor()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("signInWithEmail", "signInWithEmail:success")
                    val user =  FirebaseAuth.getInstance().currentUser!!
                    continuation.resume(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("signInWithEmail", "signInWithEmail:failure", task.exception)
                    continuation.resumeWithException(Exception(task.exception))
                }
            }
    }

    override suspend fun getUser(): FirebaseUser? {
       return FirebaseAuth.getInstance().currentUser
    }

    override suspend fun logout() {
       FirebaseAuth.getInstance().signOut()
    }


    private fun getExecutor()  = object : Executor {
        override fun execute(r: Runnable) {
            Thread(r).start()
        }

    }
}
