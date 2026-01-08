package com.vadejurisbr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LawAdapter(
    private var laws: List<LawModel>,
    private val onClick: (LawModel) -> Unit
) : RecyclerView.Adapter<LawAdapter.LawViewHolder>() {

    class LawViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.lawTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_law, parent, false)
        return LawViewHolder(view)
    }

    override fun onBindViewHolder(holder: LawViewHolder, position: Int) {
        val law = laws[position]
        holder.titleTextView.text = law.title
        holder.itemView.setOnClickListener {
            onClick(law)
        }
    }

    override fun getItemCount(): Int = laws.size

    fun updateList(newList: List<LawModel>) {
        laws = newList
        notifyDataSetChanged()
    }
}
