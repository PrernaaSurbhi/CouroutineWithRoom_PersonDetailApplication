package com.example.couroutinewithroom.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by PrernaSurbhi on 11/02/22.
 */
@Entity(tableName = "persons",
    indices = [Index(value = ["firstName", "lastName", "rank"], unique = true)]
)
@Parcelize
data class Person(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @SerializedName("first") val firstName: String = "",
    @SerializedName("last") val lastName: String = "",
    @SerializedName("rank") val rank: Int = 1,
    @SerializedName("age") val age: Int = 0,
    @SerializedName("points") val points: Int = 0,
    @SerializedName("gender") val gender: String = "",
    @SerializedName("country") val country: String = "",
    @SerializedName("image_url") val imageUrl: String = "",
    @SerializedName("description") val description: String = "",
    var favorite: Boolean = false
) : Parcelable
