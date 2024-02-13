package com.example.hiveapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hive_table")
data class Hive(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val uId: Int,
    var queenId: Int,
    val name: String,
    var type: Int,
    val lastOverview: Int,
    val familyType: Int,
    val created: Long,
    val edited: Long,
)