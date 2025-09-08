package com.example.financeseducation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tbl_users")
data class Users(
    @PrimaryKey(true) var id: Long = 0,
    var nome: String = ""
)
