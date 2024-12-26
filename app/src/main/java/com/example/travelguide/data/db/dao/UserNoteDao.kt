package com.example.travelguide.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.travelguide.data.db.entities.UserNote
import kotlinx.coroutines.flow.Flow

@Dao
interface UserNoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserNote(note: UserNote)


    //Получить все заметки для достопримечательности и пользователя.
    @Query("SELECT * FROM user_notes WHERE attractionId = :attractionId AND userId = :userId")
    fun getNotesForAttraction(attractionId: Int, userId: Int): Flow<List<UserNote>>


    @Update
    suspend fun updateUserNote(note: UserNote)
}
