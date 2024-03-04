package com.example.hiveapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hive_table")
data class HiveModel(
    @PrimaryKey(autoGenerate = false) val id: String,
    val uId: String,
    val name: String,
    var type: Int,
    val familyType: Int,
    val breed: Int,
    val line: String,
    var year: Int,
    val state: Int,
    val note: String,
    val lat: Double,
    val lng: Double,
    val created: Long,
    val edited: Long
)