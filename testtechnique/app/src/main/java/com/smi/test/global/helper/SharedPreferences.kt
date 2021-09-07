package com.smi.test.global.helper

import android.content.Context
import android.text.TextUtils
import com.securepreferences.SecurePreferences
import com.smi.test.data.model.User
import com.smi.test.global.utils.*
import com.squareup.moshi.Moshi

private const val FILE_NAME_FLAG = "bingo_file_flag"


private const val TOKEN_FLAG = "1"
private const val USER_FLAG = "2"


class SharedPreferences(context: Context, val moshi: Moshi) {

    private val sharedPreferences: android.content.SharedPreferences

    init {
        DebugLog.d(TAG, "SharedPreferences init")
        sharedPreferences = SecurePreferences(context, getPassKeyStore(context), FILE_NAME_FLAG)
    }

    fun isConnected(): Boolean {
        return !TextUtils.isEmpty(getToken())
    }


    private fun setToken(token: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_FLAG, token)
        editor.commit()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_FLAG, null)
    }

    private fun getPassKeyStore(context: Context): String {
        val alias = context.applicationContext.packageName
        var pass:String?=null
        try {
            KeyStoreHelper.createKeys(context, alias)
            pass = KeyStoreHelper.getSigningKey(alias)
        } catch (e: Exception) {
            //Crashlytics.logException(e) TODO
        }
        if (pass == null) {
            pass = getDeviceSerialNumber(context)
            pass = bitShiftEntireString(pass)
        }
        return pass
    }

    fun getUser(): User? {
        return null
    }

}