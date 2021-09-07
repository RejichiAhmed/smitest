package com.smi.test.data.model

import java.io.Serializable

class Statistic : Serializable {
    var amount: Double? = 0.0
    var commission: Double? = 0.0
    var currency: String = "EUR"
    var count: Int? = 0

    constructor() {}
    internal constructor(amount: Double?, commission: Double?, currency: String,count :Int?) {
        this.amount = amount
        this.commission = commission
        this.currency = currency
        this.count = count
    }
}