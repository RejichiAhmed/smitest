package com.smi.test.data.repository.abs

import com.google.firebase.auth.FirebaseUser


interface UserRepository {
    abstract suspend fun signUp(name: String, lastName: String, email: String, password: String) : FirebaseUser
    abstract suspend fun signIn(email: String, password: String): FirebaseUser
    abstract suspend fun getUser(): FirebaseUser?
    abstract suspend fun logout()

}
