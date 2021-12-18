package com.bracketcove.devicemanager.domain

import android.os.Parcel
import android.os.Parcelable

data class Device(
    val id: Int,
    val name: String,
    val type: DEVICE_TYPE,
    var isFavourite: Boolean,
    val imageUrl: String,
    val description: String,
    val status: STATUS,
    val os:String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        DEVICE_TYPE.values()[parcel.readInt()],
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        STATUS.values()[parcel.readInt()],
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(type.ordinal)
        parcel.writeByte(if (isFavourite) 1 else 0)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeInt(status.ordinal)
        parcel.writeString(os)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Device> {
        override fun createFromParcel(parcel: Parcel): Device {
            return Device(parcel)
        }

        override fun newArray(size: Int): Array<Device?> {
            return arrayOfNulls(size)
        }
    }
}

