/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.dao.EmpresaDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class FXMLFromEmpresaController implements Initializable {

    private Empresa empresa;

    private boolean isEdicion;

    private boolean isEleminar;

    private File fotografia;

    @FXML
    private Label lbUsuario;
    private Button btSwitch;
    @FXML
    private Button btGuradarInfo;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfPaginaWeb;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfRepresentante;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private Button btEliminar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btEditarEmpresa;
    @FXML
    private HBox vbBotones;
    @FXML
    private VBox vbFoto;
    @FXML
    private ImageView ivLogoEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void inicializarInformacion(Empresa empresa, boolean isEdicion) {

        tfCiudad.setEditable(false);
        tfTelefono.setEditable(false);
        tfPaginaWeb.setEditable(false);
        tfEmail.setEditable(false);
        tfNombre.setEditable(false);
        tfNombreComercial.setEditable(false);
        tfRepresentante.setEditable(false);
        tfDireccion.setEditable(false);
        tfRFC.setEditable(false);
        tfCodigoPostal.setEditable(false);
        vbFoto.setDisable(true);

        this.empresa = empresa;
        this.isEdicion = isEdicion;

        if (isEdicion) {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);

            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarEmpresa);

            tfCiudad.setText(empresa.getCiudad());
            tfTelefono.setText(empresa.getTelefono());
            tfPaginaWeb.setText(empresa.getPaginaWeb());
            tfRFC.setText(empresa.getRFC());
            tfEmail.setText(empresa.getEmail());
            tfNombre.setText(empresa.getNombre());
            tfNombreComercial.setText(empresa.getNombreComercial());
            tfRepresentante.setText(empresa.getNombreRepresentanteLegal());
            tfDireccion.setText(empresa.getDireccion());
            tfCodigoPostal.setText(empresa.getCodigoPostal());

            obtenerLogoEmpresa(this.empresa.getIdEmpresa());

        } else {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);

            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarEmpresa);

            tfCiudad.setEditable(true);
            tfTelefono.setEditable(true);
            tfPaginaWeb.setEditable(true);
            tfRFC.setEditable(true);
            tfEmail.setEditable(true);
            tfNombre.setEditable(true);
            tfNombreComercial.setEditable(true);
            tfRepresentante.setEditable(true);
            tfDireccion.setEditable(true);
            tfCodigoPostal.setEditable(true);
        }

    }

    @FXML
    private void btSubirInfromacionEmpresa(ActionEvent event) {

        if (isEdicion) {

            empresa.setCiudad(tfCiudad.getText());
            empresa.setTelefono(tfTelefono.getText());
            empresa.setPaginaWeb(tfPaginaWeb.getText());
            empresa.setEmail(tfEmail.getText());
            empresa.setNombre(tfNombre.getText());
            empresa.setNombreRepresentanteLegal(tfRepresentante.getText());
            empresa.setNombreComercial(tfNombreComercial.getText());
            empresa.setDireccion(tfDireccion.getText());
            empresa.setCodigoPostal(tfCodigoPostal.getText());

            editarEmpresa(empresa);

        } else {
            empresa = new Empresa();
            empresa.setCiudad(tfCiudad.getText());
            empresa.setTelefono(tfTelefono.getText());
            empresa.setPaginaWeb(tfPaginaWeb.getText());
            empresa.setRFC(tfRFC.getText());
            empresa.setEmail(tfEmail.getText());
            empresa.setNombre(tfNombre.getText());
            empresa.setNombreRepresentanteLegal(tfRepresentante.getText());
            empresa.setNombreComercial(tfNombreComercial.getText());
            empresa.setDireccion(tfDireccion.getText());
            empresa.setCodigoPostal(tfCodigoPostal.getText());

            registrarEmpresa(empresa);
        }

    }

    @FXML
    private void btEliminarEmpresa(ActionEvent event) {

        if (Utilidades.mostrarAlertaConfirmacion("Eliminar empresa", "¿Está seguro de eliminar la empresa?")) {
            Mensaje mensaje = EmpresaDAO.eliminarEmpresa(empresa);

            if (!mensaje.isError()) {
                Utilidades.mostrarAlertaSimple("Eliminar empresa", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                cerrarVentana();
            }else {
                Utilidades.mostrarAlertaSimple("Eliminar empresa", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            }

        } else {
            Utilidades.mostrarAlertaSimple("Operacion eliminar", "La operación eliminar se ha cancelado", Alert.AlertType.INFORMATION);
        }

    }

    private void registrarEmpresa(Empresa empresa) {
        Mensaje mensaje = EmpresaDAO.registrarEmpresa(empresa);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Empresa guardada", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al registrar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarEmpresa(Empresa empresa) {
        Mensaje mensaje = EmpresaDAO.editarEmpresa(empresa);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Empresa editada", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al editar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btCancelar(ActionEvent event) {

        if (isEdicion) {

            vbBotones.getChildren().clear();

            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);

            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarEmpresa);
            vbFoto.setDisable(true);
            tfCiudad.setEditable(false);
            tfTelefono.setEditable(false);
            tfPaginaWeb.setEditable(false);
            tfRFC.setEditable(false);
            tfEmail.setEditable(false);
            tfNombre.setEditable(false);
            tfNombreComercial.setEditable(false);
            tfRepresentante.setEditable(false);
            tfDireccion.setEditable(false);
            tfCodigoPostal.setEditable(false);
            obtenerLogoEmpresa(this.empresa.getIdEmpresa());

            tfCiudad.setText(empresa.getCiudad());
            tfTelefono.setText(empresa.getTelefono());
            tfPaginaWeb.setText(empresa.getPaginaWeb());
            tfRFC.setText(empresa.getRFC());
            tfEmail.setText(empresa.getEmail());
            tfNombre.setText(empresa.getNombre());
            tfNombreComercial.setText(empresa.getNombreComercial());
            tfRepresentante.setText(empresa.getNombreRepresentanteLegal());
            tfDireccion.setText(empresa.getDireccion());
            tfCodigoPostal.setText(empresa.getCodigoPostal());
        } else {

            try {

                Stage stagePrincipal = (Stage) tfCiudad.getScene().getWindow();
                FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLModuloEmpresa.fxml"));
                Parent vista = loadVista.load();
                FXMLModuloEmpresaController controladorHome = loadVista.getController();
                Scene scene = new Scene(vista);
                stagePrincipal.setScene(scene);
                stagePrincipal.show();

            } catch (Exception e) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    @FXML
    private void btEditarInfromacionEmpresa(ActionEvent event) {

        vbBotones.getChildren().clear();

        vbBotones.getChildren().add(btCancelar);
        vbBotones.getChildren().add(btGuradarInfo);

        vbBotones.getChildren().remove(btEliminar);
        vbBotones.getChildren().remove(btEditarEmpresa);
        vbFoto.setDisable(false);
        tfCiudad.setEditable(true);
        tfTelefono.setEditable(true);
        tfPaginaWeb.setEditable(true);
        tfRFC.setEditable(false);
        tfEmail.setEditable(true);
        tfNombre.setEditable(true);
        tfNombreComercial.setEditable(true);
        tfRepresentante.setEditable(true);
        tfDireccion.setEditable(true);
        tfCodigoPostal.setEditable(true);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tfCiudad.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btCargarLogo(ActionEvent event) {
        fotografia = mostrarDialogoSeleccion();
        if (fotografia != null) {
            mostrarImagenSeleccionada(fotografia);
        }
    }

    @FXML
    private void btSubirLogo(ActionEvent event) {
        if (fotografia != null) {
            Mensaje msj = EmpresaDAO.subirLogoEmpresa(fotografia, empresa.getIdEmpresa());
            if (!msj.isError()) {
                Utilidades.mostrarAlertaSimple("Fotografia guardad", msj.getMensaje(), Alert.AlertType.INFORMATION);
            } else {
                Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
            }
        }
    }

    private void obtenerLogoEmpresa(int idEmpresa) {

        Empresa empresaFoto = EmpresaDAO.obtenerLogoEmpresa(idEmpresa);
        if (empresaFoto != null && empresaFoto.getLogoEmpresaBase64() != null && empresaFoto.getLogoEmpresaBase64().length() > 0) {
            byte[] decodeImg = Base64.getDecoder().decode(empresaFoto.getLogoEmpresaBase64().replaceAll("\\n", ""));
            Image image = new Image(new ByteArrayInputStream(decodeImg));
            ivLogoEmpresa.setImage(image);
        }

    }

    private void mostrarImagenSeleccionada(File fotoFile) {

        try {

            BufferedImage bufferedImage = ImageIO.read(fotoFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ivLogoEmpresa.setImage(image);

        } catch (IOException e) {

            Utilidades.mostrarAlertaSimple("Error al cargar", "Error al intentar mostrar la imagen seleccionada", Alert.AlertType.ERROR);

        }
    }

    private File mostrarDialogoSeleccion() {
        FileChooser dialogoSeleccionImg = new FileChooser();
        dialogoSeleccionImg.setTitle("Selecciona una imagen");
        //Configuración para restringir tipos de archivos
        FileChooser.ExtensionFilter filtroArchivos = new FileChooser.ExtensionFilter("Archivos PNG (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        dialogoSeleccionImg.getExtensionFilters().add(filtroArchivos);
        //Obtener imagen
        Stage escenario = (Stage) tfNombre.getScene().getWindow();
        return dialogoSeleccionImg.showOpenDialog(escenario);
    }

}
