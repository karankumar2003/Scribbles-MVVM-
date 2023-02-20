package com.example.scribbles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RecyclerViewAdapter(private val listener: RecyclerViewItemListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        val viewHolder = MyViewHolder(view)
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.noteTextView.text = currentNote.text

    }

    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val noteTextView : TextView = itemView.findViewById(R.id.textView)
        val deleteButton : ImageView = itemView.findViewById(R.id.trashCan)
    }


}

interface RecyclerViewItemListener {
    fun onItemClick(note:Note)
}