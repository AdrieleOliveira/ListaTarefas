package com.example.listatarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listatarefas.adapters.TarefaAdapter
import com.example.listatarefas.adapters.TarefaAdapterListener
import com.example.listatarefas.model.Tarefa
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TarefaAdapterListener {

    private lateinit var adapter: TarefaAdapter
    private var positionSelected: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btRemover.visibility = View.INVISIBLE

        adapter = TarefaAdapter(this)
        listTarefa.adapter = adapter
        listTarefa.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        btSalvar.setOnClickListener {
            val tarefa = Tarefa(
                editTitulo.text.toString(),
                editDescricao.text.toString(),
                switchFinalizada.isChecked
            )

            var position : Int = 0

            if(this.positionSelected >= 0){
                adapter.edit(tarefa, this.positionSelected)
                position = this.positionSelected
            } else {
                position = adapter.add(tarefa)
            }

            (listTarefa.layoutManager as LinearLayoutManager).scrollToPosition(position)

            limpar()
        }

        btRemover.setOnClickListener{
            adapter.remove(this.positionSelected)
            (listTarefa.layoutManager as LinearLayoutManager).scrollToPosition(0)
            limpar()
        }
    }

    fun limpar(){
        editTitulo.setText(null)
        editDescricao.setText(null)
        switchFinalizada.isChecked = false
        this.positionSelected = -1
        btRemover.visibility = View.INVISIBLE

        editTitulo.requestFocus()
    }

    override fun onTarefaSelected(tarefa: Tarefa, position: Int) {
        editTitulo.setText(tarefa.titulo)
        editDescricao.setText(tarefa.descricao)
        switchFinalizada.isChecked = tarefa.finalizada

        this.positionSelected = position
        btRemover.visibility = View.VISIBLE
    }
}