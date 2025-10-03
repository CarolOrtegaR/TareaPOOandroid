package com.example.myapplication.domain

// data class: genera equals(), hashCode(), toString() y copy() automáticamente
// Constructor primario "private": obliga a crear instancias usando la fábrica create()
// Hereda de Entity y por eso usamos "override val id"
data class Task private constructor(
    override val id: Int,   // ID inmutable; viene de la superclase Entity
    val title: String       // título de la tarea (inmutable en este ejemplo)
) : Entity(id) {            // herencia: pasamos id al constructor de Entity

    // companion object: espacio "estático" asociado a la clase (no a instancias)
    companion object {
        // Contador autoincremental para asignar IDs únicos a cada Task
        // "private" => encapsulado: solo Task puede tocarlo
        private var nextId: Int = 1

        // Fábrica pública: única forma de crear Task desde afuera
        // Aplica validación y normalización antes de construir
        fun create(title: String): Task {
            // Validación: el título no puede ser vacío o solo espacios
            require(title.isNotBlank()) { "El título no puede estar vacío" }

            // Normalización: quitamos espacios al inicio/fin
            val clean = title.trim()

            // Construcción usando el constructor privado y el ID actual
            val t = Task(nextId, clean)

            // Preparamos el siguiente ID para la próxima Task
            nextId += 1

            // Devolvemos la instancia creada
            return t
        }
    }
}
