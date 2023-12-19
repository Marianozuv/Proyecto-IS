package validator;

import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;


public class EmpresaValidator {
    private static boolean isNullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }

    public static Mensaje isValid(Empresa empresa) {

        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");

        if (isNullOrEmpty(empresa.getNombre())) {
            response.setMensaje("Nombre");
        }

        if (isNullOrEmpty(empresa.getNombreComercial())) {
            response.setMensaje("Nombre Comercial");
        }

        if (isNullOrEmpty(empresa.getNombreRepresentanteLegal())) {
            response.setMensaje("Nombre del Representante Legal");
        }

        if (isNullOrEmpty(empresa.getTelefono())) {
            response.setMensaje("Telefono");
        }

        if (isNullOrEmpty(empresa.getEmail())) {
            response.setMensaje("Email");
        }

        if (isNullOrEmpty(empresa.getDireccion())) {
            response.setMensaje("Direccion");
        }

        if (isNullOrEmpty(empresa.getCodigoPostal())) {
            response.setMensaje("Codigo Postal");
        }

        if (isNullOrEmpty(empresa.getCiudad())) {
            response.setMensaje("Ciudad");
        }

        if (isNullOrEmpty(empresa.getPaginaWeb())) {
            response.setMensaje("Pagina Web");
        }

        if (isNullOrEmpty(empresa.getRFC())) {
            response.setMensaje("RFC");
        }

        if (!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() + " no puede ser vacio");
        }

        return response;
    }
}
