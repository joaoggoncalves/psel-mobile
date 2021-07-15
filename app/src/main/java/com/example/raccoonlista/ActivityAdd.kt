package com.example.raccoonlista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider

class ActivityAdd : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val tarefaViewModel2 = ViewModelProvider(this).get(TarefaViewModel::class.java)
        val ab: ActionBar? = supportActionBar
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true)
        }
        val btnAdiciona: Button = findViewById(R.id.btnInsere)
        val editTextDescr: EditText = findViewById(R.id.descrInsert)
        val editTextData: EditText = findViewById(R.id.dataInsert)
        val tvErro: TextView = findViewById(R.id.textErro)
        var descricao: String
        var dataObtida: String
        var tarefaTmp: Tarefa
        btnAdiciona.setOnClickListener { view ->
            descricao = editTextDescr.text.toString()
            dataObtida = editTextData.text.toString()
            if (descricao.length > 65 || descricao.length == 0) {
                tvErro.visibility = View.VISIBLE
            } else if (dataObtida.length > 15 || dataObtida.length == 0) {
                tvErro.visibility = View.VISIBLE
            } else {
                tvErro.visibility = View.INVISIBLE
                tarefaTmp = Tarefa(descr=descricao, data=dataObtida, id=0, status = "Incompleto")
                tarefaViewModel2.insereTarefa(tarefaTmp)
                val intentVolta = Intent(this, MainActivity::class.java)
                startActivity(intentVolta)
            }
        }
    }
}