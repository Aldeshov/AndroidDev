package com.example.todo.model

import android.os.Parcel
import android.os.Parcelable

data class ToDo(
    var completed: Boolean,
    val id: Int,
    val title: String?,
    val userId: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (completed) 1 else 0)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ToDo> {
        override fun createFromParcel(parcel: Parcel): ToDo {
            return ToDo(parcel)
        }

        override fun newArray(size: Int): Array<ToDo?> {
            return arrayOfNulls(size)
        }
    }
}