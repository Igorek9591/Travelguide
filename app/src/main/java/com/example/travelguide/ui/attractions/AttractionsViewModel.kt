package com.example.travelguide.ui.attractions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelguide.data.db.dao.AttractionDao
import com.example.travelguide.data.db.entities.Attraction
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AttractionsViewModel(private val dao: AttractionDao) : ViewModel() {

    val attractions = dao.getAllAttractions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addAttraction(attraction: Attraction) {
        viewModelScope.launch {
            dao.insertAttraction(attraction)
        }
    }

}
