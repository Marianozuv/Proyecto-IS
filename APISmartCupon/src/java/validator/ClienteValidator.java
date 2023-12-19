package validator;

import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;


public class ClienteValidator {
    private static boolean isNullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }
    
    public static Mensaje isValid(Cliente cliente) {

        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");

        if (isNullOrEmpty(cliente.getNombre())) {
            response.setMensaje("Nombre");
        }

        if (isNullOrEmpty(cliente.getApellidoPaterno())) {
            response.setMensaje("Apellido Paterno");
        }

        if (isNullOrEmpty(cliente.getApellidoMaterno())) {
            response.setMensaje("Apellido Materno");
        }

        if (isNullOrEmpty(cliente.getTelefono())) {
            response.setMensaje("Telefono");
        }

        if (isNullOrEmpty(cliente.getEmail())) {
            response.setMensaje("Email");
        }

        if (isNullOrEmpty(cliente.getDireccion())) {
            response.setMensaje("Direccion");
        }
        
        if (isNullOrEmpty(cliente.getFechaNacimiento())) {
            response.setMensaje("Fecha de Nacimiento");
        }

        if (isNullOrEmpty(cliente.getPassword())) {
            response.setMensaje("Password");
        }

        if (!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() + " no puede ser vacio");
        }

        return response;
    }
}
