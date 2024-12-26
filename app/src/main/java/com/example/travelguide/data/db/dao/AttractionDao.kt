package com.example.travelguide.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelguide.data.db.entities.Attraction
import kotlinx.coroutines.flow.Flow

@Dao
interface AttractionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttraction(attraction: Attraction)

    @Query("SELECT * FROM attractions")
    fun getAllAttractions(): Flow<List<Attraction>>

    @Query("UPDATE attractions SET visited = :visited WHERE id = :attractionId")
    suspend fun updateVisited(attractionId: Int, visited: Boolean)

    @Query("SELECT * FROM attractions WHERE id = :attractionId")
    fun getAttractionById(attractionId: Int): Flow<Attraction?>

}
