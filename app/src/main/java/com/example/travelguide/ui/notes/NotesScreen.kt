package com.example.travelguide.ui.notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelguide.data.db.entities.UserNote
import com.example.travelguide.ui.components.NoteItem
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    userId: Int,
    attractionId: Int,
    viewModel: NotesViewModel = getViewModel()
) {
    // Получаем информацию о достопримечательности
    val attraction by viewModel.getAttraction(attractionId).collectAsState(initial = null)

    // Получаем заметки пользователя
    val userNotes = viewModel.getNotesForAttraction(attractionId, userId)
        .collectAsState(initial = emptyList())

    // Поля для новой заметки
    var newNoteText by remember { mutableStateOf("") }
    var newNoteVisited by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Заметки: ${attraction?.title ?: "Загрузка..."}") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (newNoteText.isNotBlank()) {
                        // Создаем новую заметку
                        val newNote = UserNote(
                            userId = userId,
                            attractionId = attractionId,
                            note = newNoteText,
                            visited = newNoteVisited
                        )
                        viewModel.addNote(newNote)

                        // Очищаем поля
                        newNoteText = ""
                        newNoteVisited = false
                    }
                }
            ) {
                Text("Добавить заметку")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Показываем описание достопримечательности
            attraction?.let {
                Text(
                    text = it.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Ввод новой заметки
            OutlinedTextField(
                value = newNoteText,
                onValueChange = { newNoteText = it },
                label = { Text("Введите заметку") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Переключатель "Посещено"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Checkbox(checked = newNoteVisited, onCheckedChange = { newNoteVisited = it })
                Text("Посещено")
            }

            // Список заметок
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(userNotes.value) { note ->
                    NoteItem(
                        note = note,
                        onUpdateNote = { updatedNote -> viewModel.updateNote(updatedNote) }
                    )
                }
            }
        }
    }
}
