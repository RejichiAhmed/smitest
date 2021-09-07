package com.smi.test.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Brands(
    var offerId: Long? = null,
    var name: String? = null,
    var displayName: String? = null,
    var pic: String? = "https",
    var description:String? = "",
    var premium: Boolean? = null,
    var isNew: Boolean? = null
) :Parcelable {
    constructor(source: Parcel) : this(
    source.readLong(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readValue(Boolean::class.java.classLoader) as? Boolean?,
    source.readValue(Boolean::class.java.classLoader) as? Boolean?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(offerId!!)
        writeString(displayName)
        writeString(name)
        writeString(pic)
        writeString(description)
        writeValue(premium)
        writeValue(isNew)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Brands> = object : Parcelable.Creator<Brands> {
            override fun createFromParcel(source: Parcel): Brands = Brands(source)
            override fun newArray(size: Int): Array<Brands?> = arrayOfNulls(size)
        }
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "offerId" to offerId,
            "name" to name,
            "pic" to pic,
            "isNew" to isNew,
            "premium" to premium
        )
    }
}