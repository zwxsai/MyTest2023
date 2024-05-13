package com.example.mytest2023.model.overall

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *Created by 钟文祥 on 2024/2/29.
 *Describer:
 */
data class ParceInfo(
	var name: String?, var age: Int
) : Parcelable {

	
	constructor(parcel: Parcel) : this(
		parcel.readString(), parcel.readInt()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeInt(age)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ParceInfo> {
		override fun createFromParcel(parcel: Parcel): ParceInfo {
			return ParceInfo(parcel)
		}

		override fun newArray(size: Int): Array<ParceInfo?> {
			return arrayOfNulls(size)
		}
	}

}