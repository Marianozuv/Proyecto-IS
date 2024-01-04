package com.example.smartcuponapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.smartcuponapp.databinding.ActivityPerfilUsuarioBinding
import com.example.smartcuponapp.poko.Cliente
import com.example.smartcuponapp.poko.Mensaje
import com.example.smartcuponapp.utils.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class PerfilUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilUsuarioBinding
    private lateinit var cliente: Cliente
    private var isEdicion: Boolean = false
    private var fechaNacimiento : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilUsuarioBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar!!.hide()

        val clienteJson = intent.getStringExtra("cliente")

        if (clienteJson != null && clienteJson.isNotEmpty()) {
            serializarCliente(clienteJson)
            mostrarDatosCliente()
            destructurarFecha(cliente.fechaNacimiento)
            habilitarComponentes(isEdicion)
        }

        binding.btEditarCuenta.setOnClickListener {
            habilitarComponentes(!isEdicion)
        }

        binding.btCancelarEditar.setOnClickListener {
            habilitarComponentes(isEdicion)
            mostrarDatosCliente()
        }

        binding.btCambiarPassword.setOnClickListener {
            camposPassword()
        }

        binding.btGuardarCuenta.setOnClickListener {
            if (binding.etNombreUsr.isEnabled){
                this.isEdicion = true
                recuperarDatosCliente(cliente)
            }else{
                this.isEdicion = false
                recuperarDatosCliente(cliente)
            }

        }

    }

    fun camposPassword(){
        binding.etPasswordConfirmUsr.visibility = View.VISIBLE
        binding.etPasswordUsr.visibility = View.VISIBLE
        binding.btEditarCuenta.visibility = View.GONE
        binding.btCambiarPassword.visibility = View.GONE
        binding.btCancelarEditar.visibility = View.VISIBLE
        binding.btGuardarCuenta.visibility = View.VISIBLE


    }

    fun recuperarDatosCliente(cliente: Cliente){

        if(isEdicion){
            val nombre = binding.etNombreUsr.text.toString()
            val apellidoPaterno = binding.etApellidoPaternoUsr.text.toString()
            val apellidoMaterno = binding.etApellidoMaternoUsr.text.toString()
            val telefono = binding.etTelefonoUsr.text.toString()
            val direccion = binding.etDireccionUsr.text.toString()
            obtenerFecha(binding.edDiaUsr.text.toString(),binding.edMesUsr.text.toString(),binding.edYearUsr.text.toString())

            if (validarCampos(nombre, apellidoPaterno, apellidoMaterno, telefono, direccion, fechaNacimiento)){
                cliente.nombre = nombre
                cliente.apellidoMaterno = apellidoMaterno
                cliente.apellidoPaterno = apellidoPaterno
                cliente.telefono = telefono
                cliente.direccion = direccion
                peticionEditarCliente(cliente)
            }else{
                this.isEdicion = false
            }

        }else{

            val password = binding.etPasswordUsr.text.toString()
            val passwordConfirm = binding.etPasswordConfirmUsr.text.toString()

            if (verificarPassword(password, passwordConfirm)){
                cliente.password = passwordConfirm
                peticionEditarCliente(cliente)
            }

        }

    }

    fun peticionEditarCliente(cliente: Cliente){
        val gson = Gson()
        val clienteGson = gson.toJson(cliente)

        Ion.with(this@PerfilUsuarioActivity)
            .load("PUT", Constantes.URL_WS+"cliente/editar")
            .setHeader("Content-Type", "application/json")
            .setStringBody(clienteGson)
            .asString()
            .setCallback { e, result ->
                if(e == null && result != null){
                    serializarRespuestaEdicion(result)
                }else{
                    Toast.makeText(this@PerfilUsuarioActivity, "Error en la petición", Toast.LENGTH_LONG).show()
                }

            }
    }

    fun serializarRespuestaEdicion(json: String){

        val gson = Gson()
        val respuesta = gson.fromJson(json, Mensaje::class.java)

        if(!respuesta.error){
            Toast.makeText(this@PerfilUsuarioActivity, respuesta.mensaje, Toast.LENGTH_LONG).show()
            this.isEdicion = false
        }else{
            Toast.makeText(this@PerfilUsuarioActivity, respuesta.mensaje, Toast.LENGTH_LONG).show()
        }
    }

    fun serializarCliente(json : String){
        val gson = Gson()
        cliente = gson.fromJson(json, Cliente::class.java)
    }

    fun mostrarDatosCliente(){
        binding.etNombreUsr.setText(cliente.nombre)
        binding.etApellidoPaternoUsr.setText(cliente.apellidoPaterno)
        binding.etApellidoMaternoUsr.setText(cliente.apellidoMaterno)
        binding.etTelefonoUsr.setText(cliente.telefono)
        binding.etDireccionUsr.setText(cliente.direccion)
    }

    fun destructurarFecha(fecha : String){
        val separador = fecha.split("-")

        binding.edYearUsr.setText(separador[0])
        binding.edMesUsr.setText(separador[1])
        binding.edDiaUsr.setText(separador[2])

    }

    fun habilitarComponentes(isEdicion :Boolean){

        binding.etNombreUsr.isEnabled = isEdicion
        binding.etApellidoPaternoUsr.isEnabled = isEdicion
        binding.etApellidoMaternoUsr.isEnabled = isEdicion
        binding.etTelefonoUsr.isEnabled = isEdicion
        binding.etDireccionUsr.isEnabled = isEdicion
        binding.edYearUsr.isEnabled = isEdicion
        binding.edMesUsr.isEnabled = isEdicion
        binding.edDiaUsr.isEnabled = isEdicion

        if (isEdicion){

            binding.btEditarCuenta.visibility = View.GONE
            binding.btCancelarEditar.visibility = View.VISIBLE
            binding.btGuardarCuenta.visibility = View.VISIBLE
            binding.btCambiarPassword.visibility = View.GONE


        }else{

            binding.btEditarCuenta.visibility = View.VISIBLE
            binding.etPasswordConfirmUsr.visibility = View.GONE
            binding.etPasswordUsr.visibility = View.GONE
            binding.btCancelarEditar.visibility = View.GONE
            binding.btGuardarCuenta.visibility = View.GONE
            binding.btCambiarPassword.visibility = View.VISIBLE
            this.isEdicion = false
        }

    }

    fun obtenerFecha(dia : String, mes : String, year : String){

        var isValido = true

        if (dia.isEmpty()){
            isValido = false
            binding.edDiaUsr.error = "Ingresa un día"
        }

        if (mes.isEmpty()){
            isValido = false
            binding.edMesUsr.error = "Ingresa un mes"
        }

        if (year.isEmpty()){
            isValido = false
            binding.edYearUsr.error = "Ingresa un año"
        }

        if (isValido){
            fechaNacimiento = year+"/"+mes+"/"+dia
        }

    }

    fun validarCampos(nombre : String, apellidoPaterno : String, apellidoMaterno : String,
                      telefono : String, direccion : String, fechaNacimiento : String) : Boolean {
        var isValido = true

        if (nombre.isEmpty()){
            isValido = false
            binding.etNombreUsr.error = "El nombre es obligatorio"
        }

        if (apellidoPaterno.isEmpty()){
            isValido = false
            binding.etApellidoPaternoUsr.error = "El apellido paterno es obligatorio"
        }

        if (apellidoMaterno.isEmpty()){
            isValido = false
            binding.etApellidoMaternoUsr.error = "El apellido materno es obligatorio"
        }

        if (telefono.isEmpty()){
            isValido = false
            binding.etTelefonoUsr.error = "El telefono es obligatorio"
        }

        if (direccion.isEmpty()){
            isValido = false
            binding.etDireccionUsr.error = "La dirección es obligatoria"
        }

        if (fechaNacimiento.isEmpty() && fechaNacimiento == null){
            isValido = false
            binding.tvFechaUsr.error = "La fecha de naciemiento es obliagotria"
        }

        return isValido
    }

    fun verificarPassword( password: String, passwordConfirm: String) : Boolean{

        var isValido = true

        if (password.isEmpty()){
            isValido = false
            binding.etPasswordUsr.error = "La contraseña es obligatoria"
        }

        if (passwordConfirm.isEmpty()){
            isValido = false
            binding.etPasswordConfirmUsr.error = "Confirme la contraseña"
        }

        if (!password.equals(passwordConfirm)){
            isValido = false
            binding.etPasswordConfirmUsr.error = "La contraseña no coincide"
        }

        return  isValido
    }
}