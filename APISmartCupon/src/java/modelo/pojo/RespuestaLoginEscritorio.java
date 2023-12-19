package modelo.pojo;


public class RespuestaLoginEscritorio {
    
    private boolean error;
    private String contenido;
    private Usuario usuarioSesion;

    public RespuestaLoginEscritorio() {
    }

    public RespuestaLoginEscritorio(boolean error, String contenido, Usuario usuarioSesion) {
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


    
}
