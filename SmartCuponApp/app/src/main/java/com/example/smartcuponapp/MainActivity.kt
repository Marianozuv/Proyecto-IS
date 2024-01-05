package com.example.smartcuponapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcuponapp.adapters.CategoriasAdapter
import com.example.smartcuponapp.databinding.ActivityMainBinding
import com.example.smartcuponapp.interfaces.NotificacionCategoria
import com.example.smartcuponapp.poko.Categoria
import com.example.smartcuponapp.poko.Cliente
import com.example.smartcuponapp.utils.Constantes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion

class MainActivity : AppCompatActivity(), NotificacionCategoria {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cliente: Cliente
    private var categorias : ArrayList<Categoria> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()
        val jsonCliente = intent.getStringExtra("cliente")
        if(jsonCliente != null){
            serializarCliente(jsonCliente)
            cargarDatosCliente()
            cargarCategorias()
        }

        binding.btVerPerfil.setOnClickListener {
            val intent = Intent(this@MainActivity, PerfilUsuarioActivity::class.java)
            val gson = Gson()
            val clienteJson = gson.toJson(cliente)
            intent.putExtra("cliente", clienteJson)
            startActivity(intent)
        }

    }

    fun cargarCategorias(){
        Ion.with(this@MainActivity)
            .load("GET", Constantes.URL_WS+"categorias/obtenerCategorias")
            .asString()
            .setCallback { e, result ->
                if (e == null && result != null){
                    serializarCategorias(result)
                    mostrarCategorias()
                }else{
                    Toast.makeText(this@MainActivity, "Error al cargar las categorias", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun serializarCategorias(json : String){
        val gson = Gson()
        val typeCategoria = object : TypeToken<ArrayList<Categoria>>(){}.type
        categorias = gson.fromJson(json, typeCategoria)
        Toast.makeText(this@MainActivity, "Categorias: ${categorias.size}", Toast.LENGTH_SHORT).show()
    }

    fun mostrarCategorias(){
        binding.recyclerCategorias.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerCategorias.setHasFixedSize(true)
        if(categorias.size > 0){
            binding.tvDefault.visibility = View.GONE
            binding.recyclerCategorias.adapter = CategoriasAdapter(categorias, this)
        }
    }

    fun serializarCliente(json : String){
        val gson = Gson()
        cliente = gson.fromJson(json, Cliente::class.java)
    }

    fun cargarDatosCliente(){
        binding.tvNombreUsr.text = cliente.nombre
    }

    override fun clickItemLista(posicion: Int, categoria: Categoria) {
        val alerta = AlertDialog.Builder(this@MainActivity)
        alerta.setTitle("Categoria: ${categoria.categoria}")
        alerta.setMessage("Dese ver las promociones de ${categoria.categoria}")
        alerta.setPositiveButton("Ver promociones", DialogInterface.OnClickListener { dialog, i ->
            Toast.makeText(this@MainActivity, "Ir a pantalla promociones", Toast.LENGTH_LONG).show()
            val intent = Intent(this@MainActivity, ListaPromocionesActivity::class.java)
            val idCategoria = categoria.idCategoria
            intent.putExtra("idCategoria", idCategoria)
            startActivity(intent)
        })

        alerta.setNegativeButton("Cancelar", null)
        val dialog = alerta.create().show()
    }


}