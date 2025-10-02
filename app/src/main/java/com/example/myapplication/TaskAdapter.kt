package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.Task

class TaskAdapter(
    private val data: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    class TaskVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskVH(v)
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val t = data[position]
        holder.tvTitle.text = "• #${t.id}  ${t.title}"
    }

    override fun getItemCount(): Int = data.size

    fun addTask(t: Task) {
        data.add(t)
        notifyItemInserted(data.size - 1)
    }
}