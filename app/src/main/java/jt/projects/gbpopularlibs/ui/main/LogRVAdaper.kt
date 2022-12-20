package jt.projects.gbpopularlibs.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.gbpopularlibs.databinding.ItemLogBinding

class LogRVAdaper : RecyclerView.Adapter<LogRVAdaper.LogViewHolder>() {

    var data: MutableList<String> = mutableListOf()


    fun addLog(text: String) {
        data.add(0, text)
        this.notifyItemInserted(0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder =
        LogViewHolder(
            ItemLogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class LogViewHolder(private val binding: ItemLogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            binding.tvItemLog.text = text
        }
    }

}