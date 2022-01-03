package com.confuseddevs.ahmcricfinale.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Keep
@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var firstName: String,
    val wins: Int,
    val losses: Int,
    val draws: Int
): Parcelable