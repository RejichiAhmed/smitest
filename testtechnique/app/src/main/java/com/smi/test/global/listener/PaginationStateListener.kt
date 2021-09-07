package com.smi.test.global.listener

import com.smi.test.global.enumeration.State

interface PaginationStateListener {
    fun setState(newState: State)
}
