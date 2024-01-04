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
public class PromocionSucursal {
    
    private int idPromocion;
    private int idSucursal;

    public PromocionSucursal() {
    }

    public PromocionSucursal(int idPromocion, int idSucursal) {
        this.idPromocion = idPromocion;
        this.idSucursal = idSucursal;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    
    
}
