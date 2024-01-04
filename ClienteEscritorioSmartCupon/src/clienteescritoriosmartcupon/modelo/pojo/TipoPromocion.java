/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo.pojo;


public class TipoPromocion {
    
    private int idTipoPromocion;
    private String tipoPoromocion;

    public TipoPromocion() {
    }

    public TipoPromocion(int idTipoPromocion, String tipoPoromocion) {
        this.idTipoPromocion = idTipoPromocion;
        this.tipoPoromocion = tipoPoromocion;
    }

    public int getIdTipoPromocion() {
        return idTipoPromocion;
    }

    public void setIdTipoPromocion(int idTipoPromocion) {
        this.idTipoPromocion = idTipoPromocion;
    }

    public String getTipoPoromocion() {
        return tipoPoromocion;
    }

    public void setTipoPoromocion(String TipoPoromocion) {
        this.tipoPoromocion = TipoPoromocion;
    }

    @Override
    public String toString() {
        return "- " + tipoPoromocion; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
