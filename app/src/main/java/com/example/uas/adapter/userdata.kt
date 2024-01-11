package com.example.uas.adapter

import android.os.Parcel
import android.os.Parcelable

data class DataItem(
    val id: Int,
    val nama: String,
    val noHp: String,
    val email: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nama)
        parcel.writeString(noHp)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataItem> {
        override fun createFromParcel(parcel: Parcel): DataItem {
            return DataItem(parcel)
        }

        override fun newArray(size: Int): Array<DataItem?> {
            return arrayOfNulls(size)
        }
    }
}
