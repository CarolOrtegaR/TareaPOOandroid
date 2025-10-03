package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.Task

// Adapter: puente entre la lista de datos (List<Task>) y las vistas del RecyclerView.
// Se encarga de crear "celdas" (ViewHolder) y de vincular cada Task con su vista.
class TaskAdapter(
    // Fuente de datos mutable que el adapter mostrará.
    // MutableList para poder agregar elementos y notificar cambios.
    private val data: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    // ViewHolder: referencia a las vistas de una fila para reciclarlas eficientemente.
    // Recibe la vista ya inflada (item_task.xml) y busca sus sub-vistas por id.
    class TaskVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    }

    // Crea un nuevo ViewHolder cuando el RecyclerView lo necesita.
    // Infla el layout XML de la fila (item_task.xml) sin adjuntarlo aún al padre.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskVH(v)
    }

    // Vincula (bind) los datos de la posición 'position' con el ViewHolder 'holder'.
    // Aquí decides qué texto/imagen mostrar en la celda.
    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val t = data[position]                 // obtiene la Task de esa posición
        holder.tvTitle.text = "• #${t.id}  ${t.title}"  // setea el texto en el TextView
    }

    // Cantidad total de filas que debe mostrar el RecyclerView.
    override fun getItemCount(): Int = data.size

    // API del adapter para agregar una nueva Task al final y notificar el cambio.
    // Notificar con notifyItemInserted permite animaciones y actualización eficiente.
    fun addTask(t: Task) {
        data.add(t)
        notifyItemInserted(data.size - 1)
    }
}
