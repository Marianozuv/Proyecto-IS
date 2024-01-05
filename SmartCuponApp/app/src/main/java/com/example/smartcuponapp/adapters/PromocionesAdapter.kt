package com.example.smartcuponapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcuponapp.R
import com.example.smartcuponapp.interfaces.NotificacionPromocion
import com.example.smartcuponapp.poko.Promocion

class PromocionesAdapter(val promociones : ArrayList<Promocion>, val observador : NotificacionPromocion) : RecyclerView.Adapter<PromocionesAdapter.ViewHolderPromociones>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPromociones {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_lista_promociones, parent, false)
        return ViewHolderPromociones(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderPromociones, position: Int) {
        val promocion = promociones[position]
        holder.tvNombre.text = promocion.nombrePromocion
        holder.tvDescripcion.text = promocion.descripcion

        holder.cardItem.setOnClickListener {
            observador.clickItemLista(position, promocion)
        }
    }

    override fun getItemCount(): Int {
        return promociones.size
    }

    class ViewHolderPromociones (itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvNombre : TextView = itemView.findViewById(R.id.tv_promocion)
        val tvDescripcion : TextView = itemView.findViewById(R.id.tv_descripcion)
        val cardItem : CardView = itemView.findViewById(R.id.card_item_promocion)
    }

}