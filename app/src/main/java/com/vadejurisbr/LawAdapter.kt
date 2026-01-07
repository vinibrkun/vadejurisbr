package com.vadejurisbr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LawAdapter(private var laws: List<LawModel>, private val onClick: (LawModel) -> Unit) :
    RecyclerView.Adapter<LawAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.lawTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_law, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val law = laws[position]
        holder.title.text = law.title
        holder.itemView.setOnClickListener { onClick(law) }
    }

    override fun getItemCount() = laws.size

    fun updateList(newList: List<LawModel>) {
        laws = newList
        notifyDataSetChanged()
    }
}
