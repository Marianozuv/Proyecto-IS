/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

/**
 *
 * @author mateo
 */
public class TipoPromocion {
    
    private int idTipoPromocion;
    private String TipoPoromocion;

    public TipoPromocion() {
    }

    public TipoPromocion(int idTipoPromocion, String TipoPoromocion) {
        this.idTipoPromocion = idTipoPromocion;
        this.TipoPoromocion = TipoPoromocion;
    }

    public int getIdTipoPromocion() {
        return idTipoPromocion;
    }

    public void setIdTipoPromocion(int idTipoPromocion) {
        this.idTipoPromocion = idTipoPromocion;
    }

    public String getTipoPoromocion() {
        return TipoPoromocion;
    }

    public void setTipoPoromocion(String TipoPoromocion) {
        this.TipoPoromocion = TipoPoromocion;
    }
    
}
