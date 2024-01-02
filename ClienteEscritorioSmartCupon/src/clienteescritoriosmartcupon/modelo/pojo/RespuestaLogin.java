package clienteescritoriosmartcupon.modelo.pojo;

public class RespuestaLogin {
    
    private boolean error;
    private String contenido;
    private Usuario usuarioSesion;

    public RespuestaLogin() {
    }

    public RespuestaLogin(boolean error, String contenido, Usuario usuarioSesion) {
        this.error = error;
        this.contenido = contenido;
        this.usuarioSesion = usuarioSesion;
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

    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
    
    public boolean getError() {
        return error;
    }


    
}
