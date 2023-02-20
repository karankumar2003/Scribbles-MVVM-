package com.example.scribbles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var allNotes: LiveData<List<Note>>

    init {
        val database = NoteDatabase.getDatabase(application)
        val dao = database.getNoteDao()
        val repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }
}