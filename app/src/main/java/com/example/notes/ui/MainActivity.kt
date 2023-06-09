package com.example.notes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.data.entity.Note

class MainActivity : AppCompatActivity(), INotesRvAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getting the Edit Text
        val inputText=findViewById<TextView>(R.id.input);
        //initializing recyclerView...
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter= NotesRVAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateList(it)
            }
        })
    }

    override  fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted",Toast.LENGTH_LONG).show();
    }

    fun submitData(view: View) {
      val inputText=findViewById<TextView>(R.id.input);
        val noteText=inputText.text.toString();
       if(noteText.isNotEmpty()){
           viewModel.insertNote(Note(noteText))
           Toast.makeText(this, "$noteText Inserted",Toast.LENGTH_LONG).show();
       }

    }
}