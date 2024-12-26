package com.example.travelguide.ui.di

import android.app.Application
import androidx.room.Room
import com.example.travelguide.data.db.AppDatabase
import com.example.travelguide.data.db.dao.AttractionDao
import com.example.travelguide.data.db.dao.UserNoteDao
import com.example.travelguide.ui.attractions.AttractionsViewModel
import com.example.travelguide.ui.notes.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Инициализация базы данных
    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            "travel_guide.db"
        ).build()
    }

    // DAO
    single<AttractionDao> { get<AppDatabase>().attractionDao() }
    single<UserNoteDao> { get<AppDatabase>().userNoteDao() }


    // ViewModels
    viewModel { AttractionsViewModel(get()) }
    viewModel { NotesViewModel(get<UserNoteDao>(), get<AttractionDao>()) }
}
