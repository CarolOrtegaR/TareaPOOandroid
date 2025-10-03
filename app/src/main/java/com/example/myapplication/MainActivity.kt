package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.Task

// Activity principal: orquesta la pantalla y coordina UI ↔ lógica.
class MainActivity : AppCompatActivity() {

    // Lista en memoria con las tareas (modelo de datos simple)
    private val tasks = mutableListOf<Task>()

    // Adaptador del RecyclerView (se inicializa en onCreate)
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout activity_main.xml (etTask, btnAdd, tvResult, rvTasks)
        setContentView(R.layout.activity_main)

        // -------- Referencias a la UI --------
        val etTask = findViewById<EditText>(R.id.etTask)      // campo de texto para escribir tarea
        val btnAdd = findViewById<Button>(R.id.btnAdd)        // botón "Agregar"
        val tvResult = findViewById<TextView>(R.id.tvResult)  // muestra la última tarea creada
        val rv = findViewById<RecyclerView>(R.id.rvTasks)     // lista (RecyclerView)

        // -------- Configuración del RecyclerView --------
        adapter = TaskAdapter(tasks)                 // puente datos↔vistas
        rv.layoutManager = LinearLayoutManager(this) // lista vertical simple
        rv.adapter = adapter

        // -------- Lógica del botón "Agregar" --------
        btnAdd.setOnClickListener {
            val title = etTask.text.toString()

            // Validación mínima: no permitir texto vacío
            if (title.isBlank()) {
                etTask.error = "Escribe una tarea"
                return@setOnClickListener
            }

            // 1) Crear Task usando la fábrica (ID autoincremental dentro de Task)
            val nueva = Task.create(title)

            // 2) Agregarla al origen de datos y notificar al adapter
            tasks.add(nueva)
            adapter.notifyItemInserted(tasks.size - 1)

            // 3) Actualizar el rótulo con la última tarea creada
            tvResult.text = "Última tarea → #${nueva.id}: ${nueva.title}"

            // 4) Limpiar el campo de texto
            etTask.text.clear()
        }
    }
}
