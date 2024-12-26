package com.example.travelguide.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_notes")
data class UserNote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val attractionId: Int,
    val note: String,
    val visited: Boolean
)
