package com.example.afinal.models

import android.os.Parcel
import android.os.Parcelable

data class News(
    val articles: ArrayList<Article>,
    val status: String?,
    val totalResults: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
            arrayListOf<Article>().apply {
                parcel.readArrayList(Article::class.java.classLoader)
            },
            parcel.readString(),
            parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeInt(totalResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }

}