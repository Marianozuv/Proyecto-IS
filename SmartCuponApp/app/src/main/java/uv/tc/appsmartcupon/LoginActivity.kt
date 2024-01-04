package com.example.smartcuponapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.smartcuponapp.databinding.ActivityLoginBinding
import com.example.smartcuponapp.poko.Cliente
import com.example.smartcuponapp.poko.RespuestaLoginApp
import com.example.smartcuponapp.utils.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view  = binding.root
        setContentView(view)

        supportActionBar!!.hide()

        binding.btInciciarSesion.setOnClickListener {
            val email = binding.etCorreo.text.toString()
            val password = binding.edPassword.text.toString()

            if (validarCamposLogin(email,password)){
                peticionLogin(email, password)
            }
        }

        binding.btCrearCuenta.setOnClickListener {
            val intent = Intent(this@LoginActivity, CrearCuentaActivity::class.java)
            startActivity(intent)
        }

    }


    fun validarCamposLogin(email : String, password : String) : Boolean {

        var isValido = true

        if(email.isEmpty()){
            isValido = false
            binding.etCorreo.error = "El coreo es obligatorio"
        }

        if (password.isEmpty()){
            isValido = false
            binding.edPassword.error = "La contraseña es obligatoria"
        }

        return isValido

    }

    fun peticionLogin(email: String, password: String){

        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false)

        Ion.with(this@LoginActivity)
            .load("POST", Constantes.URL_WS+"autenticacion/loginApp")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("email", email)
            .setBodyParameter("password", password)
            .asString()
            .setCallback { e, result ->
                if(e == null && result != null){
                    serializarRespuestaLogin(result)
                }else{
                    Toast.makeText(this@LoginActivity, "Error en la petición", Toast.LENGTH_LONG).show()
                }
            }
    }


    fun serializarRespuestaLogin(json : String){
        val gson = Gson()
        val respuesta : RespuestaLoginApp = gson.fromJson(json, RespuestaLoginApp::class.java)
        Toast.makeText(this@LoginActivity, respuesta.contenido, Toast.LENGTH_LONG).show()
        if(!respuesta.error){
            irPantallaPrincipal(respuesta.clienteSesion)
        }
    }


    fun irPantallaPrincipal(cliente : Cliente){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        val gson = Gson()
        val clienteJson = gson.toJson(cliente)
        intent.putExtra("cliente", clienteJson)
        startActivity(intent)
        finish()
    }
}