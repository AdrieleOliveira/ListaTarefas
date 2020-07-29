package com.example.listatarefas.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.listatarefas.R
import com.example.listatarefas.model.Tarefa
import kotlinx.android.synthetic.main.item_tarefa.view.*

class TarefaAdapter(val listener: TarefaAdapterListener) :
    RecyclerView.Adapter<TarefaAdapter.ViewHolder>() {
        private val tarefas = mutableListOf<Tarefa>()

        fun add(tarefa: Tarefa): Int {
            val position = itemCount
            tarefas.add(position, tarefa)
            notifyItemInserted(position)
            return position
        }

        fun edit(tarefa: Tarefa, position: Int){
            tarefas.set(position, tarefa)
            notifyItemChanged(position)
        }

        fun remove(position: Int){
            tarefas.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_tarefa, parent, false)
        )

    override fun getItemCount() = tarefas.size;

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.fillView(tarefa, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun fillView(tarefa: Tarefa, position: Int){
            itemView.lbTitulo.text = tarefa.titulo
            itemView.lbDescricao.text = tarefa.descricao
            itemView.checkFinalizada.isChecked = tarefa.finalizada

            itemView.setOnClickListener {
                listener.onTarefaSelected(tarefa, position)
            }
        }
    }
}