package com.example.raccoonlista

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TarefaViewModel(application: Application): AndroidViewModel(application) {
    private val repositorio: TarefaRepo
    var getAll: LiveData<List<Tarefa>>
    init {
        val tarefaDB = TarefaDatabase.getDatabase(application).tarefaDAO()
        repositorio = TarefaRepo(tarefaDB)
        getAll = repositorio.tudo
    }

    fun insereTarefa(tarefa: Tarefa) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.insert(tarefa)
        }
    }

    fun deleteTarefa(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.delete(id)
        }
    }

    fun updateTarefa(idupdate: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.update(idupdate)
        }
    }
}