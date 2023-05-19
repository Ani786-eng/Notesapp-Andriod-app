package com.example.notes.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.data.entity.Note
import java.util.ArrayList

class NotesRVAdapter(private val context: Context, private val listener: INotesRvAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    val allNotes=ArrayList<Note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemView.findViewById<ImageView>(R.id.deleteButton)


    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        //helps in creating the items.
      val viewHolder=  NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
      return viewHolder

    }

    override fun getItemCount(): Int {
        //give the count of the items
        return allNotes.size

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        //bind the data to the item.
        val currentNote=allNotes[position]
        //setting up text
        holder.textView.text=currentNote.text

    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        //update the list
        notifyDataSetChanged()
    }
}

interface INotesRvAdapter{
    fun onItemClicked(note: Note){

    }
}