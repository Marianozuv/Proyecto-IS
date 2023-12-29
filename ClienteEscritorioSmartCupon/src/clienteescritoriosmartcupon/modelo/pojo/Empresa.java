/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon.modelo.pojo;


public class Empresa {

    private Integer idEmpresa;
    private String nombre;
    private String nombreComercial;
    private byte[] logoEmpresa;
    private String logoEmpresaBase64;
    private String nombreRepresentanteLegal;
    private String email;
    private String direccion;
    private String codigoPostal;
    private String ciudad;
    private String telefono;
    private String paginaWeb;
    private String RFC;
    private String estatus;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa, String nombre, String nombreComercial, byte[] logoEmpresa, String logoEmpresaBase64, String nombreRepresentanteLegal, String email, String direccion, String codigoPostal, String ciudad, String telefono, String paginaWeb, String RFC, String estatus) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nombreComercial = nombreComercial;
        this.logoEmpresa = logoEmpresa;
        this.logoEmpresaBase64 = logoEmpresaBase64;
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
        this.email = email;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
        this.RFC = RFC;
        this.estatus = estatus;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public byte[] getLogoEmpresa() {
        return logoEmpresa;
    }

    public void setLogoEmpresa(byte[] logoEmpresa) {
        this.logoEmpresa = logoEmpresa;
    }

    public String getLogoEmpresaBase64() {
        return logoEmpresaBase64;
    }

    public void setLogoEmpresaBase64(String logoEmpresaBase64) {
        this.logoEmpresaBase64 = logoEmpresaBase64;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    @Override
    public String toString() {
        return "- "+ nombre; //To change body of generated methods, choose Tools | Templates.
    }
}
