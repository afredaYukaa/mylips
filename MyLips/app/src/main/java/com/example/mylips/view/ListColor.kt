package com.example.mylips.view

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class ListColor(
    val link: String?,
    val name: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(link)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListColor> {
        override fun createFromParcel(parcel: Parcel): ListColor {
            return ListColor(parcel)
        }

        override fun newArray(size: Int): Array<ListColor?> {
            return arrayOfNulls(size)
        }
    }
}
