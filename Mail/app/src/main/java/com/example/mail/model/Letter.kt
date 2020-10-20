package com.example.mail.model

import android.os.Parcel
import android.os.Parcelable

data class Letter(var id: String, var subject: String, var body: String, var is_read: Boolean = false): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(subject)
        parcel.writeString(body)
        parcel.writeByte(if (is_read) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Letter> {
        override fun createFromParcel(parcel: Parcel): Letter {
            return Letter(parcel)
        }

        override fun newArray(size: Int): Array<Letter?> {
            return arrayOfNulls(size)
        }
    }
}