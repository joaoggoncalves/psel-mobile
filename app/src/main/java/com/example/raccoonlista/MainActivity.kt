package com.example.raccoonlista

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val lista = ArrayList<CardClasse>()
    private val adaptador = Adaptador(lista)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addBtn: FloatingActionButton = findViewById(R.id.addbotao)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val semTarefas: TextView = findViewById(R.id.textSemTarefa)
        val tarefaViewModel = ViewModelProvider(this).get(TarefaViewModel::class.java)
        recyclerView.adapter = adaptador
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        addBtn.setOnClickListener { view ->
            val intentAdd = Intent(this, ActivityAdd::class.java)
            startActivity(intentAdd)
        }
        tarefaViewModel.getAll.observe(this,  { tarefas ->
            var tarefaCard: CardClasse
            for (i in 0..(tarefas.size-1) step 1) {
                tarefaCard = CardClasse(tarefas[i].descr, tarefas[i].data, tarefas[i].id.toString(), tarefas[i].status)
                if (!lista.contains(tarefaCard)) {
                    lista.add(i, tarefaCard)
                    adaptador.notifyItemInserted(i)
                }
            }
            if (lista.isEmpty()) {
                semTarefas.visibility = View.VISIBLE
            } else {
                semTarefas.visibility = View.INVISIBLE
            }
        })
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val tarefaViewModel = ViewModelProvider(this).get(TarefaViewModel::class.java)
        val indice = item.groupId
        val id = lista[indice].id?.toInt()
        if(item.itemId == 1) {
            if (id != null) {
                tarefaViewModel.deleteTarefa(id)
                lista.removeAt(indice)
                adaptador.notifyItemRemoved(indice)
            }
        } else if (item.itemId == 0) {
            if (id != null) {
                tarefaViewModel.updateTarefa(id)
                lista.removeAt(indice)
                adaptador.notifyItemRemoved(indice)
            }
        }
        return super.onContextItemSelected(item)
    }
}