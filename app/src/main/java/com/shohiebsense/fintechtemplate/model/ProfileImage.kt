package com.shohiebsense.fintechtemplate.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Shohiebsense on 01/03/2018.
 */
class ProfileImage (val id: String, val bucketName : String, val name : String, val path : String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(bucketName)
        parcel.writeString(name)
        parcel.writeString(path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProfileImage> {
        override fun createFromParcel(parcel: Parcel): ProfileImage {
            return ProfileImage(parcel)
        }

        override fun newArray(size: Int): Array<ProfileImage?> {
            return arrayOfNulls(size)
        }
    }


}