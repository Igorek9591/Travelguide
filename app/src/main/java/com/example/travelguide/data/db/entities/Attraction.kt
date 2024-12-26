package com.example.travelguide.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attractions")
data class Attraction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val location: String,
    val type: String,
    val visited: Boolean = false,
)
