package com.example.travelguide.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelguide.data.db.dao.AttractionDao
import com.example.travelguide.data.db.dao.UserNoteDao
import com.example.travelguide.data.db.entities.Attraction
import com.example.travelguide.data.db.entities.UserNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val userNoteDao: UserNoteDao,
    private val attractionDao: AttractionDao
) : ViewModel() {


    fun getAttraction(attractionId: Int): Flow<Attraction?> {
        return attractionDao.getAttractionById(attractionId)
    }

    fun getNotesForAttraction(attractionId: Int, userId: Int): Flow<List<UserNote>> {
        return userNoteDao.getNotesForAttraction(attractionId, userId)
    }


    fun updateNote(note: UserNote) {
        viewModelScope.launch {
            userNoteDao.updateUserNote(note)
        }
    }

    fun addNote(note: UserNote) {
        viewModelScope.launch {
            userNoteDao.insertUserNote(note)
        }
    }

}

