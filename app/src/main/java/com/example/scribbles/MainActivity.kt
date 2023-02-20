package com.example.scribbles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RecyclerViewItemListener {

    lateinit var viewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerViewAdapter
    lateinit var input: EditText
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager =LinearLayoutManager(this,RecyclerView.VERTICAL,true)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        input = findViewById(R.id.inputText)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }

        })

    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if (noteText.isNotBlank()) {
            viewModel.insertNote(Note(noteText))
        }
        input.setText("")
    }
}