package com.simplyfleet.assignment.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user WHERE uid = :userId")
    fun deleteByUserId(userId: Int)

    @Query("SELECT * FROM user WHERE uid = :userId")
    fun getUserbyid(userId: Int): List<User>?

    //Update userid
    @Query("UPDATE user SET name = :name,mobile = :mobile,email = :email,address = :address,address1 = :address1 WHERE uid =:id")
    fun UpdateUserDetails(name: String?,mobile: String?,email: String?,address: String?,address1: String?, id: Int)
}