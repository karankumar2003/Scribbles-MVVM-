package com.example.scribbles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var allNotes: LiveData<List<Note>>
lateinit var repository:NoteRepository
    init {
        val database = NoteDatabase.getDatabase(application)
        val dao = database.getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note:Note)= viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}