package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.Task

class MainActivity : AppCompatActivity() {

    private val tasks = mutableListOf<Task>()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // usa tu layout con etTask, btnAdd, tvResult, rvTasks

        // Referencias de UI
        val etTask = findViewById<EditText>(R.id.etTask)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val rv = findViewById<RecyclerView>(R.id.rvTasks)

        // RecyclerView
        adapter = TaskAdapter(tasks)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        // Botón Agregar
        btnAdd.setOnClickListener {
            val title = etTask.text.toString()
            if (title.isBlank()) {
                etTask.error = "Escribe una tarea"
                return@setOnClickListener
            }
            val nueva = Task.create(title)          // ID autoincremental: 1,2,3...
            tasks.add(nueva)
            adapter.notifyItemInserted(tasks.size - 1)
            tvResult.text = "Última tarea → #${nueva.id}: ${nueva.title}"
            etTask.text.clear()
        }
    }
}