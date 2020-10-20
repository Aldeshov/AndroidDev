package com.example.midterm.model

import android.os.Parcel
import android.os.Parcelable

data class ToDoItem(var id: String, var title: String, var description: String,
                    var status: Boolean, var category: String, var duration: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeString(category)
        parcel.writeString(duration)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ToDoItem> {
        override fun createFromParcel(parcel: Parcel): ToDoItem {
            return ToDoItem(parcel)
        }

        override fun newArray(size: Int): Array<ToDoItem?> {
            return arrayOfNulls(size)
        }
    }

}