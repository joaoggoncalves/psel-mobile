package com.example.raccoonlista

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TarefaAcesso {

    @Query("SELECT * FROM table_tarefas")
    fun getAll(): LiveData<List<Tarefa>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tarefa: Tarefa)

    @Query("DELETE FROM table_tarefas WHERE id= :id")
    fun deleteId(id: Int)

    @Query("UPDATE table_tarefas SET status = 'Completo' WHERE id= :idupdate")
    fun updateId(idupdate: Int)
}