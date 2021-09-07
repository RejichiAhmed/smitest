package com.smi.test.base


import com.smi.test.global.helper.SharedPreferences

/**
 * this is the base repository class, all project repositories should extends this class.
 */
abstract class BaseRepository(protected val sharedPreferences: SharedPreferences)
