package com.example.smartcuponapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcuponapp.adapters.PromocionesAdapter
import com.example.smartcuponapp.databinding.ActivityListaPromocionesBinding
import com.example.smartcuponapp.interfaces.NotificacionPromocion
import com.example.smartcuponapp.poko.Categoria
import com.example.smartcuponapp.poko.Promocion
import com.example.smartcuponapp.utils.Constantes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion
import java.util.*
import kotlin.collections.ArrayList

class ListaPromocionesActivity : AppCompatActivity(), NotificacionPromocion {

    private lateinit var binding: ActivityListaPromocionesBinding
    private var idCategoria = 0
    private var promociones : ArrayList<Promocion> = ArrayList()
    private lateinit var searchView: SearchView
    private lateinit var adapter: PromocionesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaPromocionesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()
        idCategoria = intent.getIntExtra("idCategoria", 0)

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val texto = s.toString()
                val empresa: String
                val fecha: String

                if (texto.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {

                    empresa = ""
                    fecha = texto
                } else {

                    empresa = texto
                    fecha = ""
                }

                adapter.filtrarPorEmpresaYFecha(empresa, fecha)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })




        if (idCategoria > 0){
            //Toast.makeText(this@ListaPromocionesActivity, "Se recupero el idCategoria", Toast.LENGTH_SHORT).show()
            obtenerPromociones(idCategoria)
        }else{
            //Toast.makeText(this@ListaPromocionesActivity, "No se recupero el idCategoria", Toast.LENGTH_SHORT).show()
        }


    }
    
    fun obtenerPromociones(idCategoria: Int){
        Ion.with(this@ListaPromocionesActivity)
            .load("GET", "${Constantes.URL_WS}promocion/listaPromocionPorCategoriaId/${idCategoria}")
            .asString()
            .setCallback { e, result ->  
                if (e == null && result != null){
                    serializarInfromaciónPromociones(result)
                    mostrarInformacionLista()
                }else{
                    Toast.makeText(this@ListaPromocionesActivity, "Error al mostrar las promociones", Toast.LENGTH_SHORT).show()
                }
            }
    }
    
    
    fun serializarInfromaciónPromociones(json : String){
        val gson = Gson()
        val typePromociones = object : TypeToken<ArrayList<Promocion>>(){}.type
        promociones = gson.fromJson(json, typePromociones)
        //Toast.makeText(this@ListaPromocionesActivity, "Promociones ${promociones.size}", Toast.LENGTH_SHORT).show()
    }

    /*fun mostrarInformacionLista(){
        binding.recyclerPromociones.layoutManager = LinearLayoutManager(this@ListaPromocionesActivity)
        binding.recyclerPromociones.setHasFixedSize(true)
        if(promociones.size > 0){
            binding.tvDefault.visibility = View.GONE
            binding.recyclerPromociones.adapter = PromocionesAdapter(promociones, this)
        }
    }*/

    fun mostrarInformacionLista() {
        binding.recyclerPromociones.layoutManager = LinearLayoutManager(this@ListaPromocionesActivity)
        binding.recyclerPromociones.setHasFixedSize(true)

        // Inicializar el adaptador solo si no está inicializado aún
        if (!::adapter.isInitialized) {
            adapter = PromocionesAdapter(promociones, this)
            binding.recyclerPromociones.adapter = adapter
        }

        if (promociones.size > 0) {
            binding.tvDefault.visibility = View.GONE
            adapter.notifyDataSetChanged()  // Asegúrate de notificar que los datos han cambiado
        }
    }



    override fun clickItemLista(posicion: Int, promocion: Promocion) {
        val alerta = AlertDialog.Builder(this@ListaPromocionesActivity)
        alerta.setTitle("Detalles promoción")
        alerta.setMessage("Desea ver los detalles de la promoción?")
        alerta.setPositiveButton("Ver detalles", DialogInterface.OnClickListener { dialog, i ->
            val intent = Intent(this@ListaPromocionesActivity, DetallePromocionActivity::class.java)
            val gson = Gson()
            val promocionJson = gson.toJson(promocion)
            intent.putExtra("promocion", promocionJson)
            startActivity(intent)
        })

        alerta.setNegativeButton("Cancelar", null)
        val dialogo = alerta.create().show()
    }


}
