package com.example.smartcuponapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcuponapp.databinding.ActivityCrearCuentaBinding
import com.example.smartcuponapp.databinding.ActivityLoginBinding
import com.example.smartcuponapp.poko.Cliente
import com.example.smartcuponapp.poko.Mensaje
import com.example.smartcuponapp.utils.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import java.util.*

class CrearCuentaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearCuentaBinding
    private var fechaNacimiento: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.btRegistrarCuenta.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val apellidoPaterno = binding.etApellidoPaterno.text.toString()
            val apellidoMaterno = binding.etApellidoPaterno.text.toString()
            val telefono = binding.etTelefono.text.toString()
            val email = binding.etEmail.text.toString()
            val direccion = binding.etDireccion.text.toString()
            obtenerFecha(binding.edDia.text.toString(),binding.edMes.text.toString(),binding.edYear.text.toString())
            val password = binding.edPassword.text.toString()
            val passwordConfirm = binding.edPasswordConfirm.text.toString()

            if (validarCampos(nombre, apellidoPaterno, apellidoMaterno, telefono, email, direccion, fechaNacimiento, password, passwordConfirm)
                              && verificarPassword(password, passwordConfirm)){
                val cliente = Cliente(nombre, apellidoPaterno, apellidoMaterno, telefono, email, direccion, fechaNacimiento, password)

                Toast.makeText(this@CrearCuentaActivity, "Datos correctos", Toast.LENGTH_LONG).show()
                realizarPeticionRegistro(cliente)
            }

        }
    }

    fun realizarPeticionRegistro(cliente: Cliente){

        val gson = Gson()
        val clienteGson = gson.toJson(cliente)

        Ion.with(this@CrearCuentaActivity)
            .load("POST", Constantes.URL_WS+"cliente/registrar")
            .setHeader("Content-Type", "application/json")
            .setStringBody(clienteGson)
            .asString()
            .setCallback { e, result ->
                if(e == null && result != null){
                    serializarRespuestaRegistro(result)
                }else{
                    Toast.makeText(this@CrearCuentaActivity, "Error en la petición", Toast.LENGTH_LONG).show()
                }

            }

    }

    fun serializarRespuestaRegistro(json: String){

        val gson = Gson()
        val respuesta = gson.fromJson(json, Mensaje::class.java)

        if(!respuesta.error){
            Toast.makeText(this@CrearCuentaActivity, respuesta.mensaje, Toast.LENGTH_LONG).show()
            pantallaLogin()
        }else{
            Toast.makeText(this@CrearCuentaActivity, respuesta.mensaje, Toast.LENGTH_LONG).show()
        }
    }

    fun validarCampos(nombre : String, apellidoPaterno : String, apellidoMaterno : String,
                      telefono : String, email : String, direccion : String, fechaNacimiento : String,
                      password : String, passwordConfirm : String) : Boolean {
        var isValido = true

        if (nombre.isEmpty()){
            isValido = false
            binding.etNombre.error = "El nombre es obligatorio"
        }

        if (apellidoPaterno.isEmpty()){
            isValido = false
            binding.etApellidoPaterno.error = "El apellido paterno es obligatorio"
        }

        if (apellidoMaterno.isEmpty()){
            isValido = false
            binding.etApellidoMaterno.error = "El apellido materno es obligatorio"
        }

        if (telefono.isEmpty()){
            isValido = false
            binding.etTelefono.error = "El telefono es obligatorio"
        }

        if (email.isEmpty()){
            isValido = false
            binding.etEmail.error = "El email es obligatorio"
        }

        if (direccion.isEmpty()){
            isValido = false
            binding.etDireccion.error = "La dirección es obligatoria"
        }

        if (password.isEmpty()){
            isValido = false
            binding.edPassword.error = "La contraseña es obligatoria"
        }

        if (passwordConfirm.isEmpty()){
            isValido = false
            binding.edPasswordConfirm.error = "Confirme la contraseña"
        }

        if (fechaNacimiento.isEmpty() && fechaNacimiento == null){
            isValido = false
            binding.tvFecha.error = "La fecha de naciemiento es obliagotria"
        }



        return isValido
    }


    fun obtenerFecha(dia : String, mes : String, year : String){

        var isValido = true

        if (dia.isEmpty()){
            isValido = false
            binding.edDia.error = "Ingresa un día"
        }

        if (mes.isEmpty()){
            isValido = false
            binding.edMes.error = "Ingresa un mes"
        }

        if (year.isEmpty()){
            isValido = false
            binding.edYear.error = "Ingresa un año"
        }

        if (isValido){
            fechaNacimiento = year+"/"+mes+"/"+dia
        }

    }

    fun verificarPassword( password: String, passwordConfirm: String) : Boolean{

        var isValido = true

        if (!password.equals(passwordConfirm)){
            isValido = false
            binding.edPasswordConfirm.error = "La contraseña no coincide"
        }

        return  isValido
    }

    fun pantallaLogin(){
        val intent = Intent(this@CrearCuentaActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}