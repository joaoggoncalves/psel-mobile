package com.example.raccoonlista

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tarefas", )
data class Tarefa(@PrimaryKey(autoGenerate = true) val id: Int,
                  val descr: String,
                  val data: String,
                  val status: String
)