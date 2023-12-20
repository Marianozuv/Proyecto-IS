/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo.pojo;

/**
 *
 * @author mateo
 */
public class CodigoHTTP {

    private int codigoRespuesta;
    private String Contenido;

    public CodigoHTTP() {
    }

    public CodigoHTTP(int codigoRespuesta, String Contenido) {
        this.codigoRespuesta = codigoRespuesta;
        this.Contenido = Contenido;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

}

