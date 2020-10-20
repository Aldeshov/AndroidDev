package com.example.mail.model

import android.os.Parcel
import android.os.Parcelable

data class Mail(var id: String, var email: String, var mail: ArrayList<Letter> = ArrayList<Letter>()): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        arrayListOf<Letter>().apply {
            parcel.readArrayList(Letter::class.java.classLoader)
        }
    ) {
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(email)
        dest?.writeList(mail)
    }

    companion object CREATOR : Parcelable.Creator<Mail> {
        override fun createFromParcel(parcel: Parcel): Mail {
            return Mail(parcel)
        }

        override fun newArray(size: Int): Array<Mail?> {
            return arrayOfNulls(size)
        }
    }
}