package com.appsian.composenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsian.composenoteapp.data.NotesDataSource
import com.appsian.composenoteapp.model.Note
import com.appsian.composenoteapp.screens.NoteScreen
import com.appsian.composenoteapp.screens.NoteViewModel
import com.appsian.composenoteapp.ui.theme.ComposeNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val notesViewModel: NoteViewModel by viewModels()
                    NotesApp(notesViewModel)
                }
            }
        }
    }
}

@Composable
fun NotesApp(notesViewModel: NoteViewModel = viewModel()) {
    val notesList = notesViewModel.noteList
    NoteScreen(
        notes = notesList,
        onAddNote = {
            notesViewModel.addNote(it)
        },
        onRemoveNote = {
            notesViewModel.removeNote(it)
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeNoteAppTheme {
    }
}