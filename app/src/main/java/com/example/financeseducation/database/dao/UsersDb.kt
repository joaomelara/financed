package com.example.financeseducation.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.financeseducation.model.Users

@Database([Users::class], version = 1)
abstract class UsersDb: RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object{

        private lateinit var instance: UsersDb

        fun getDatabase(context: Context): UsersDb{
            if(!::instance.isInitialized){
                instance = Room
                    .databaseBuilder(
                        context,
                        UsersDb::class.java,
                        "users_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance

        }

    }

}