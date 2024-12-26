package com.example.travelguide.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelguide.data.db.entities.UserNote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: UserNote,
    onUpdateNote: (UserNote) -> Unit
) {
    // Локальное состояние отвечает за то, редактируем ли мы заметку или просто её смотрим
    var isEditing by remember { mutableStateOf(false) }

    // Для редактирования текста и флага visited используем локальные переменные
    var noteText by remember { mutableStateOf(note.note) }
    var visited by remember { mutableStateOf(note.visited) }

    Card(
        // При нажатии переключаемся «просмотр» <-> «редактирование»
        onClick = {
            isEditing = !isEditing
        },
        modifier = Modifier.padding(8.dp)
    ) {
        // Внутри карточки смотрим: если isEditing = true, показываем поля ввода
        if (isEditing) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Редактирование заметки:", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    label = { Text("Текст заметки") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                // Переключатель «Посещено»
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Checkbox(checked = visited, onCheckedChange = { visited = it })
                    Text(text = "Посещено")
                }

                // Кнопка «Сохранить» сохранит изменения и вернёт карточку в режим просмотра
                Button(
                    onClick = {
                        // Обновляем запись, вызывая колбэк
                        onUpdateNote(note.copy(note = noteText, visited = visited))
                        // Снова показываем «простой» вид
                        isEditing = false
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Сохранить")
                }
            }
        } else {
            // Режим просмотра заметки
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = " $noteText",
                    style = MaterialTheme.typography.bodyMedium
                )

                if (visited) {
                    Text(
                        text = "Посещено",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = "(Нажмите, чтобы изменить)",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
