package modelo.pojo;

public class Cupon {
    private Integer idCupon;
    private Integer codigoPromocion;
    private String fechaCanje;
    
    
    public Cupon() {
    }

    public Cupon(Integer idCupon, Integer codigoPromocion, String fechaCanje) {
        this.idCupon = idCupon;
        this.codigoPromocion = codigoPromocion;
        this.fechaCanje = fechaCanje;
    }

    public Integer getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(Integer idCupon) {
        this.idCupon = idCupon;
    }

    public Integer getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(Integer codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(String fechaCanje) {
        this.fechaCanje = fechaCanje;
    }
    
}
