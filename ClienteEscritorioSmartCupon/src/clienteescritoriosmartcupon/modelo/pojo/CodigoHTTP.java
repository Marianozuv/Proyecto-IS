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

    private int condigoRespuesta;
    private String Contenido;

    public CodigoHTTP() {
    }

    public CodigoHTTP(int condigoRespuesta, String Contenido) {
        this.condigoRespuesta = condigoRespuesta;
        this.Contenido = Contenido;
    }

    public int getCondigoRespuesta() {
        return condigoRespuesta;
    }

    public void setCondigoRespuesta(int condigoRespuesta) {
        this.condigoRespuesta = condigoRespuesta;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

}

