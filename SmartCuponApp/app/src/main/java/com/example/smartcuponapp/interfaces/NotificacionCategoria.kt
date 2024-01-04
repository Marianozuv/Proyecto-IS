package com.example.smartcuponapp.interfaces

import com.example.smartcuponapp.poko.Categoria
import com.example.smartcuponapp.poko.Cliente

interface NotificacionCategoria {

    fun clickItemLista(posicion : Int, categoria : Categoria)

}