package modelo.pojo;


public class RespuestaLoginApp {
    private boolean error;
    private String contenido;
    private Cliente clienteSesion;

    public RespuestaLoginApp() {
    }

    public RespuestaLoginApp(boolean error, String contenido, Cliente clienteSesion) {
        this.error = error;
        this.contenido = contenido;
        this.clienteSesion = clienteSesion;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Cliente getClienteSesion() {
        return clienteSesion;
    }

    public void setClienteSesion(Cliente clienteSesion) {
        this.clienteSesion = clienteSesion;
    }
}
