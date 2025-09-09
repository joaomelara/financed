package com.example.financeseducation.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tbl_users")
data class Users(
    @PrimaryKey(true) var id: Long = 0,
    var nome: String = "",
    var income: Double = 0.0,
    var expenses: Double = 0.0,
    var createdAt: Long = System.currentTimeMillis()
)
