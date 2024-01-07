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
public class Estatus {
    
    private boolean isEstado;
    private String nombreEstado;

    public Estatus() {
    }

    public Estatus(boolean isEstado, String nombreEstado) {
        this.isEstado = isEstado;
        this.nombreEstado = nombreEstado;
    }

    public boolean isIsEstado() {
        return isEstado;
    }

    public void setIsEstado(boolean isEstado) {
        this.isEstado = isEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    


    @Override
    public String toString() {
        return "- "+ nombreEstado ; //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
}
