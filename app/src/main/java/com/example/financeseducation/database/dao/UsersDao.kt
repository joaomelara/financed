package com.example.financeseducation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.financeseducation.model.Users

@Dao
interface UsersDao {

    @Insert
    fun save(users: Users): Long

    @Query("SELECT * FROM tbl_users LIMIT 1")
    fun showName(): Users

}