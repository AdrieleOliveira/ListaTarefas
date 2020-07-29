package com.example.listatarefas.adapters

import com.example.listatarefas.model.Tarefa

interface TarefaAdapterListener {
    fun onTarefaSelected(tarefa: Tarefa, position: Int)
}