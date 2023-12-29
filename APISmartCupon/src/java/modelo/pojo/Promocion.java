package modelo.pojo;

public class Promocion {

    private Integer idPromocion;
    private Integer idEmpresa;
    private String nombrePromocion;
    private String descripcion;
    private String imagenPromocionBase64;
    private String fechaInicioPromocion;
    private String fechaTerminoPromocion;
    private String restricciones;
    private Integer idTipoPromocion;
    private Integer porcentaje_Costo;
    private Integer idCategoria;
    private int cuponesMaximos;
    private String codigoPromocion;
    private byte[] imagenPromocion;
    private boolean estatus;

    public Promocion() {
    }

    public Promocion(Integer idPromocion, Integer idEmpresa, String nombrePromocion, String descripcion, String imagenPromocionBase64, String fechaInicioPromocion, String fechaTerminoPromocion, String restricciones, Integer idTipoPromocion, Integer porcentaje_Costo, Integer idCategoria, int cuponesMaximos, String codigoPromocion, byte[] imagenPromocion, boolean estatus) {
        this.idPromocion = idPromocion;
        this.idEmpresa = idEmpresa;
        this.nombrePromocion = nombrePromocion;
        this.descripcion = descripcion;
        this.imagenPromocionBase64 = imagenPromocionBase64;
        this.fechaInicioPromocion = fechaInicioPromocion;
        this.fechaTerminoPromocion = fechaTerminoPromocion;
        this.restricciones = restricciones;
        this.idTipoPromocion = idTipoPromocion;
        this.porcentaje_Costo = porcentaje_Costo;
        this.idCategoria = idCategoria;
        this.cuponesMaximos = cuponesMaximos;
        this.codigoPromocion = codigoPromocion;
        this.imagenPromocion = imagenPromocion;
        this.estatus = estatus;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenPromocionBase64() {
        return imagenPromocionBase64;
    }

    public void setImagenPromocionBase64(String imagenPromocionBase64) {
        this.imagenPromocionBase64 = imagenPromocionBase64;
    }

    public String getFechaInicioPromocion() {
        return fechaInicioPromocion;
    }

    public void setFechaInicioPromocion(String fechaInicioPromocion) {
        this.fechaInicioPromocion = fechaInicioPromocion;
    }

    public String getFechaTerminoPromocion() {
        return fechaTerminoPromocion;
    }

    public void setFechaTerminoPromocion(String fechaTerminoPromocion) {
        this.fechaTerminoPromocion = fechaTerminoPromocion;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public Integer getIdTipoPromocion() {
        return idTipoPromocion;
    }

    public void setIdTipoPromocion(Integer idTipoPromocion) {
        this.idTipoPromocion = idTipoPromocion;
    }

    public Integer getPorcentaje_Costo() {
        return porcentaje_Costo;
    }

    public void setPorcentaje_Costo(Integer porcentaje_Costo) {
        this.porcentaje_Costo = porcentaje_Costo;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getCuponesMaximos() {
        return cuponesMaximos;
    }

    public void setCuponesMaximos(int cuponesMaximos) {
        this.cuponesMaximos = cuponesMaximos;
    }

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public byte[] getImagenPromocion() {
        return imagenPromocion;
    }

    public void setImagenPromocion(byte[] imagenPromocion) {
        this.imagenPromocion = imagenPromocion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    
    
}
