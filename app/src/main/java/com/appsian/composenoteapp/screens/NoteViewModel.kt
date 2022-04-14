package com.appsian.composenoteapp.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsian.composenoteapp.data.NotesDataSource
import com.appsian.composenoteapp.model.Note
import com.appsian.composenoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    //    var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            noteRepository.getAllNotes().distinctUntilChanged()
                .collect{ listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()){
                        Log.d("Empty", ": Empty List")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
//        noteList.addAll(NotesDataSource().loadNotes())
    }

    suspend fun addNote(note: Note) = viewModelScope.launch { noteRepository.addNote(note) }
    suspend fun updateNote(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }
    suspend fun removeNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }


}