package com.example.raccoonlista

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adaptador(private val listaTarefas: List<CardClasse>) : RecyclerView.Adapter<Adaptador.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val tarefaView = LayoutInflater.from(parent.context).inflate(R.layout.tarefa_card, parent, false)

        return ViewHolder(tarefaView)
    }

    override fun onBindViewHolder(tarefaHolder: ViewHolder, pos: Int) {
        val tarefaAtual = listaTarefas[pos]

        tarefaHolder.textViewDescr.setText(tarefaAtual.descrTarefa)
        tarefaHolder.textViewData.setText(tarefaAtual.data)
        tarefaHolder.textViewStatus.setText(tarefaAtual.status)
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }

    inner class ViewHolder(tarefaView: View) : RecyclerView.ViewHolder(tarefaView), View.OnCreateContextMenuListener{
        val textViewDescr: TextView = tarefaView.findViewById(R.id.tarefaDescr)
        val textViewData: TextView = tarefaView.findViewById(R.id.tarefaData)
        val textViewStatus: TextView = tarefaView.findViewById(R.id.tarefaStatus)

        init {
            tarefaView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            if (menu != null) {
                menu.setHeaderTitle("Escolha uma ação")
                if (v != null) {
                    menu.add(this.adapterPosition, 0, 0, "Completar Tarefa")
                    menu.add(this.adapterPosition, 1, 1, "Remover Tarefa")
                }
            }
        }
    }
}