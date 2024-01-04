package com.example.smartcuponapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcuponapp.R
import com.example.smartcuponapp.interfaces.NotificacionCategoria
import com.example.smartcuponapp.poko.Categoria


class CategoriasAdapter(val categorias : ArrayList<Categoria>, val observador : NotificacionCategoria) : RecyclerView.Adapter<CategoriasAdapter.ViewHolderCategorias>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategorias {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_lista_categorias, parent, false)
        return ViewHolderCategorias(itemView)
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    override fun onBindViewHolder(holder: ViewHolderCategorias, position: Int) {
        val categoria = categorias[position]
        holder.nombreCategoria.text = categoria.categoria

        holder.cardItem.setOnClickListener {
            observador.clickItemLista(position, categoria)
        }
    }

    class ViewHolderCategorias (itemView : View) : RecyclerView.ViewHolder(itemView){
        val nombreCategoria : TextView = itemView.findViewById(R.id.tv_categoria)
        val cardItem : CardView = itemView.findViewById(R.id.card_item_categoria)
    }



}