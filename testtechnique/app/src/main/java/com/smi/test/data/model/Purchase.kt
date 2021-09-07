package com.smi.test.data.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class Purchase(
    var amount: Long? = null,
    var commission: String? = null,
    var currency: String? = "EUR",
    var offerId: Long? = null,) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "offerId" to offerId,
            "currency" to currency,
            "commission" to commission,
            "amount" to amount
        )
    }
}