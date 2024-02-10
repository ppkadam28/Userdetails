package com.simplyfleet.assignment.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "mobile") val mobile: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "address1") val userAddress1: String?,
    @ColumnInfo(name = "createdat") val createdat: String?


)