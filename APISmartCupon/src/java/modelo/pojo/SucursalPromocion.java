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
public class SucursalPromocion {
    
    private int idSucursal_Promocion;
    private int idSucursal;
    private int idPromocion;

    public SucursalPromocion() {
    }

    public SucursalPromocion(int idSucursal_Promocion, int idSucursal, int idPromocion) {
        this.idSucursal_Promocion = idSucursal_Promocion;
        this.idSucursal = idSucursal;
        this.idPromocion = idPromocion;
    }

    public int getIdSucursal_Promocion() {
        return idSucursal_Promocion;
    }

    public void setIdSucursal_Promocion(int idSucursal_Promocion) {
        this.idSucursal_Promocion = idSucursal_Promocion;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    
    
}
