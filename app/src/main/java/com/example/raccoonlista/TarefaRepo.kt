package com.example.raccoonlista

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class TarefaRepo(private val tarefaAcesso: TarefaAcesso) {

    val tudo: LiveData<List<Tarefa>> = tarefaAcesso.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(tarefa: Tarefa) {
        tarefaAcesso.insert(tarefa)
    }

    fun delete(id: Int) {
        tarefaAcesso.deleteId(id)
    }

    fun update(idupdate: Int) {
        tarefaAcesso.updateId(idupdate)
    }


}