package com.smi.test.global.listener

import com.smi.test.global.utils.DebugLog
import com.smi.test.global.utils.TAG


/**
 * Created on 2/2/18.
 */

interface ToolBarListener {

    fun onStartClicked() {
        DebugLog.d(TAG, "onStartClicked but not handled")
    }

    fun onEndClicked() {
        DebugLog.d(TAG, "onStartClicked but not handled")
    }
}
