package com.example.financeseducation.database.repository

import android.content.Context
import com.example.financeseducation.database.dao.UsersDb
import com.example.financeseducation.models.Users

class UsersRepository(context: Context) {

    private val db = UsersDb.getDatabase(context).usersDao()

    fun save(users: Users): Long {
        return db.save(users)
    }

    fun showName(): Users {
        return db.showName()
    }

}