package com.example.smartcuponapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcuponapp.R
import com.example.smartcuponapp.interfaces.NotificacionSucursal
import com.example.smartcuponapp.poko.Sucursal

class SucursalesAdapter(val sucursales : ArrayList<Sucursal>, val observador : NotificacionSucursal) : RecyclerView.Adapter<SucursalesAdapter.ViewHolderSucursales>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSucursales {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_lista_sucursales, parent, false)
        return ViewHolderSucursales(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderSucursales, position: Int) {
        val sucursal = sucursales[position]
        holder.tvNombreSucursal.text = sucursal.nombreSucursal
        holder.tvDireccion.text = "Dirección: ${sucursal.direccion}"
        holder.tvColonia.text = "Colonia: ${sucursal.colonia}"
        holder.tvCP.text = "CP: ${sucursal.codigoPostal}"
        holder.tvCiudad.text = "Ciudad: ${sucursal.ciudad}"
        holder.tvTelefono.text = "Teléfono: ${sucursal.telefono}"
        holder.tvEncargado.text = "Gerente: ${sucursal.nombreEncargado}"

        holder.cardItem.setOnClickListener {
            observador.clickItemLista(position, sucursal)
        }

    }

    override fun getItemCount(): Int {
        return  sucursales.size
    }

    class ViewHolderSucursales (itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvNombreSucursal : TextView = itemView.findViewById(R.id.tv_nombre_sucursal)
        val tvDireccion : TextView = itemView.findViewById(R.id.tv_direccion)
        val tvColonia : TextView = itemView.findViewById(R.id.tv_colonia)
        val tvCP : TextView = itemView.findViewById(R.id.tv_codigo_postal)
        val tvCiudad : TextView = itemView.findViewById(R.id.tv_ciudad)
        val tvTelefono : TextView = itemView.findViewById(R.id.tv_telefono)
        val tvEncargado : TextView = itemView.findViewById(R.id.tv_nombre_encargado)
        val cardItem : CardView = itemView.findViewById(R.id.card_item_sucursal)
    }

}