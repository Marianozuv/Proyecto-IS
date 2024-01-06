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

class PromocionesAdapter(var promociones: ArrayList<Promocion>, val observador: NotificacionPromocion) :
    RecyclerView.Adapter<PromocionesAdapter.ViewHolderPromociones>() {

    // Lista original sin filtrar
    private val promocionesOriginal: ArrayList<Promocion> = ArrayList(promociones)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPromociones {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lista_promociones, parent, false)
        return ViewHolderPromociones(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderPromociones, position: Int) {
        val promocion = promociones[position]
        holder.tvNombre.text = promocion.nombrePromocion
        holder.tvDescripcion.text = promocion.descripcion
        holder.tvEmpresa.text = promocion.empresa
        holder.tvFecha.text = promocion.fechaInicioPromocion + " / " + promocion.fechaTerminoPromocion

        holder.cardItem.setOnClickListener {
            observador.clickItemLista(position, promocion)
        }
    }

    override fun getItemCount(): Int {
        return promociones.size
    }

    fun filtrarPorEmpresaYFecha(filtroEmpresa: String, filtroFecha: String) {
        promociones.clear()
        promociones.addAll(promocionesOriginal.filter { promocion ->
            val empresaCoincide = promocion.empresa.contains(filtroEmpresa, true)
            val fechaCoincide = promocion.fechaTerminoPromocion.startsWith(filtroFecha)
            empresaCoincide && fechaCoincide
        })
        notifyDataSetChanged()
    }



    // Método para reiniciar la lista original
    fun reiniciarLista() {
        promociones.clear()
        promociones.addAll(promocionesOriginal)
        notifyDataSetChanged()
    }

    class ViewHolderPromociones(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tv_promocion)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tv_descripcion)
        val tvEmpresa: TextView = itemView.findViewById(R.id.tv_empresa)
        val cardItem: CardView = itemView.findViewById(R.id.card_item_promocion)
        val tvFecha: TextView = itemView.findViewById(R.id.tv_fecha)
    }
}
