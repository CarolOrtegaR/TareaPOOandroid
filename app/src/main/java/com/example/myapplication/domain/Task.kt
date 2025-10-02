package com.example.myapplication.domain

data class Task private constructor(
    override val id: Int,   // sobrescribe el id de Entity
    val title: String
) : Entity(id) {

    companion object {
        private var nextId: Int = 1

        fun create(title: String): Task {
            require(title.isNotBlank()) { "El título no puede estar vacío" }
            val clean = title.trim()
            val t = Task(nextId, clean)
            nextId += 1
            return t
        }
    }
}
