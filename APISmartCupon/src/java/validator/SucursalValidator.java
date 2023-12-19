package validator;

import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;


public class SucursalValidator {
    private static boolean isNullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }
    
    public static Mensaje isValid(Sucursal sucursal) {

        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");
        
        if (isNullOrEmpty("" + sucursal.getIdEmpresa())) {
            response.setMensaje("ID Medico");
        }

        if (isNullOrEmpty(sucursal.getNombreSucursal())) {
            response.setMensaje("Nombre Sucursal");
        }

        if (isNullOrEmpty(sucursal.getDireccion())) {
            response.setMensaje("Direccion");
        }

        if (isNullOrEmpty(sucursal.getCodigoPostal())) {
            response.setMensaje("Codigo Postal");
        }

        if (isNullOrEmpty(sucursal.getColonia())) {
            response.setMensaje("Colonia");
        }

        if (isNullOrEmpty(sucursal.getCiudad())) {
            response.setMensaje("Ciudad");
        }

        if (isNullOrEmpty(sucursal.getTelefono())) {
            response.setMensaje("Telefono");
        }

        if (isNullOrEmpty(sucursal.getLatitud())) {
            response.setMensaje("Latitud");
        }

        if (isNullOrEmpty(sucursal.getLongitud())) {
            response.setMensaje("Longitud");
        }

        if (isNullOrEmpty(sucursal.getNombreEncargado())) {
            response.setMensaje("Nombre Encargado");
        }

        if (!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() + " no puede ser vacio");
        }

        return response;
    }
}
