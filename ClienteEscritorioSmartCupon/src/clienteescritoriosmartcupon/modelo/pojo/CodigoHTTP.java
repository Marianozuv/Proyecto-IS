package clienteescritoriosmartcupon.modelo.pojo;

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

