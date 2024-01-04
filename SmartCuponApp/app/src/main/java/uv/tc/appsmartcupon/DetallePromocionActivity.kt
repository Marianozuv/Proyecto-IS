package com.example.smartcuponapp

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcuponapp.adapters.SucursalesAdapter
import com.example.smartcuponapp.databinding.ActivityDetallePromocionBinding
import com.example.smartcuponapp.interfaces.NotificacionSucursal
import com.example.smartcuponapp.poko.Promocion
import com.example.smartcuponapp.poko.Sucursal
import com.example.smartcuponapp.utils.Constantes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion

class DetallePromocionActivity : AppCompatActivity(), NotificacionSucursal {

    private lateinit var binding: ActivityDetallePromocionBinding
    private lateinit var promocion: Promocion
    private var sucursales : ArrayList<Sucursal> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePromocionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()
        val promocionJson = intent.getStringExtra("promocion")
        if (promocionJson != null){
            serializarPromocion(promocionJson)
            cargarDetallesVentas(promocion)
            cargarSucursalesAsociadas(promocion.idPromocion)
            descargarImagenPromocion(promocion.idPromocion)
        }
    }

    fun serializarPromocion(json : String){
        val gson = Gson()
        promocion = gson.fromJson(json, Promocion::class.java)
    }

    fun cargarDetallesVentas(promocion: Promocion){
        binding.tvNombrePromocion.text = promocion.nombrePromocion
        binding.tvDescripcionDetalle.text = promocion.descripcion
        binding.tvVigencia.text = "Fecha ${promocion.fechaInicioPromocion} - ${promocion.fechaTerminoPromocion}"
        binding.tvRestricciones.text = "Restricciones: ${promocion.restricciones}"
        binding.tvNumeroCupones.text = "Número de cupones: ${promocion.cuponesMaximos}"
        binding.tvCodigo.text = "CODIGO: ${promocion.codigoPromocion}"

    }
    
    fun cargarSucursalesAsociadas(idPromocion: Int){
        Ion.with(this@DetallePromocionActivity)
            .load("GET", "${Constantes.URL_WS}promocion/obtenerSucursalesByPromocion/"+idPromocion)
            .asString()
            .setCallback { e, result ->  
                if (e == null && result != null){
                    serializarSucursales(result)
                    mostrarSucursales()
                }else{
                    Toast.makeText(this@DetallePromocionActivity, "No se pudo recuperar las sucursales", Toast.LENGTH_LONG).show()
                }
            }
    }
    
    fun serializarSucursales(json : String){
        val gson = Gson()
        val typeSucursales = object  : TypeToken<ArrayList<Sucursal>>(){}.type
        sucursales = gson.fromJson(json, typeSucursales)
        Toast.makeText(this@DetallePromocionActivity, "Sucursales recuperadas: ${sucursales.size}", Toast.LENGTH_SHORT).show()
    }

    fun mostrarSucursales(){
        binding.recyclerSucursales.layoutManager = LinearLayoutManager(this@DetallePromocionActivity)
        binding.recyclerSucursales.setHasFixedSize(true)
        if (sucursales.size > 0){
            binding.tvDefault.visibility = View.GONE
            binding.recyclerSucursales.adapter = SucursalesAdapter(sucursales, this)
        }
    }

    override fun clickItemLista(posicion: Int, sucursal: Sucursal) {
        TODO("Not yet implemented")
    }

    fun descargarImagenPromocion(idPromocion: Int){
        Ion.with(this@DetallePromocionActivity)
            .load("GET", "${Constantes.URL_WS}promocion/obtenerImgen/${idPromocion}")
            .asString()
            .setCallback { e, result ->
                if (e == null && result != null){
                    mostrarImagenPromocion(result)
                }else{
                    Toast.makeText(this@DetallePromocionActivity, "Error al cargar la imagen", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun mostrarImagenPromocion(json: String){
        val gson = Gson()
        val respuestaImagen = gson.fromJson(json, Promocion::class.java)

        if(respuestaImagen.imagenPromocionBase64.isNotEmpty()){
            val imgByte = Base64.decode(respuestaImagen.imagenPromocionBase64, Base64.DEFAULT)
            val bitMapImagen = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.size)
            binding.ivPromocion.setImageBitmap(bitMapImagen)
        }
    }
}