package com.example.couroutinewithroom

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

/**
 * Created by PrernaSurbhi on 11/02/22.
 */

@Parcelize
data class PersonListItem(
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "firstName")
    val firstName: String = "",
    @ColumnInfo(name = "lastName")
    val lastName: String = "",
    @ColumnInfo(name = "country")
    val country: String = "",
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String = "",
    @ColumnInfo(name = "favorite")
    val favorite: Boolean = false
) : Parcelable


