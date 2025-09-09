package com.example.financeseducation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.financeseducation.models.Users

@Dao
interface UsersDao {

    @Insert
    fun save(users: Users): Long

    @Update
    fun saveIncomeAndExpenses(users: Users): Int

    @Query("SELECT * FROM tbl_users ORDER BY createdAt DESC LIMIT 1")
    fun showName(): Users

}