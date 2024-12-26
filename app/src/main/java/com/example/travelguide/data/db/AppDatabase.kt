package com.example.travelguide.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelguide.data.db.dao.AttractionDao
import com.example.travelguide.data.db.dao.UserNoteDao
import com.example.travelguide.data.db.entities.Attraction
import com.example.travelguide.data.db.entities.UserNote

@Database(entities = [Attraction::class, UserNote::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun attractionDao(): AttractionDao
    abstract fun userNoteDao(): UserNoteDao
}