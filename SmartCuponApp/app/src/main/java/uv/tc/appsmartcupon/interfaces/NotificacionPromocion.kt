package com.example.smartcuponapp.interfaces

import com.example.smartcuponapp.poko.Categoria
import com.example.smartcuponapp.poko.Promocion

interface NotificacionPromocion {
    fun clickItemLista(posicion : Int, promocion: Promocion)
}