package com.smi.test.global.helper

import com.smi.test.global.listener.SchedulerProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


class AppSchedulerProvider : SchedulerProvider {


    override fun dispatchersUI(): CoroutineDispatcher = Dispatchers.Main

    override fun dispatchersDefault(): CoroutineDispatcher = Dispatchers.Default

    override fun dispatchersIO(): CoroutineDispatcher = Dispatchers.IO

    override fun dispatchersUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}