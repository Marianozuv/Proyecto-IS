/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritoriosmartcupon;

import clienteescritoriosmartcupon.modelo.pojo.Categoria;
import clienteescritoriosmartcupon.modelo.pojo.Empresa;
import clienteescritoriosmartcupon.modelo.pojo.Mensaje;
import clienteescritoriosmartcupon.modelo.pojo.Promocion;
import clienteescritoriosmartcupon.modelo.pojo.TipoPromocion;
import clienteescritoriosmartcupon.modelo.pojo.dao.CategoriasDAO;
import clienteescritoriosmartcupon.modelo.pojo.dao.EmpresaDAO;
import clienteescritoriosmartcupon.modelo.pojo.dao.PromocionDAO;
import clienteescritoriosmartcupon.utils.Utilidades;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class FXMLFormPromocionController implements Initializable {

    private boolean isEstatus;
    private ObservableList<Empresa> empresas;
    private ObservableList<TipoPromocion> tipoPromociones;
    private ObservableList<Categoria> categorias;
    private boolean isEdicion;
    private Promocion promocion;
    private File imagenPromo;

    private FXMLModuloPromocionController moduloPromocionController;

    @FXML
    private Label lbUsuario;
    @FXML
    private HBox vbBotones;
    @FXML
    private Button btEliminar;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btGuradarInfo;
    @FXML
    private Button btEditarPromocion;
    @FXML
    private VBox vbFoto;
    @FXML
    private ImageView ivImagenPromocion;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfDesc;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaTermino;
    @FXML
    private TextField tfRestricciones;
    @FXML
    private ComboBox<TipoPromocion> cbTipoPromocion;
    @FXML
    private TextField tfPorcentajeCosto;
    @FXML
    private ComboBox<Categoria> cbCategorias;
    @FXML
    private TextField tfCupones;
    @FXML
    private TextField tfCodigoPromo;
    @FXML
    private ComboBox<Empresa> cbEmpresas;
    @FXML
    private TextField tfEstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setModuloPromocionController(FXMLModuloPromocionController controller) {
        this.moduloPromocionController = controller;
    }

    public void inicializarInformacion(Promocion promocion, boolean isEdicion) {

        if (isEdicion) {
            this.isEdicion = isEdicion;
            this.promocion = promocion;
            cargarDatos(promocion, isEdicion);
            obtenerImagenPromocion(promocion.getIdPromocion());
            cargarEmpresas();
            seleccionarEmpresa(promocion.getIdEmpresa());
            cargarCategorias();
            seleccionarCategoria(promocion.getIdCategoria());
            cargarTiposPromociones();
            seleccionarTipoPromocion(promocion.getIdTipoPromocion());
            if (promocion.isEstatus()) {
                tfEstatus.setText("Activo");
            }

            if (!promocion.isEstatus()) {
                tfEstatus.setText("Inactivo");
            }

            System.out.println(promocion.isEstatus());

        } else {
            this.isEdicion = isEdicion;
            this.promocion = new Promocion();
            cargarDatos(null, isEdicion);
            cargarEmpresas();
            cargarCategorias();
            cargarTiposPromociones();
            
            if (!empresas.isEmpty()) {
                cbEmpresas.setValue(empresas.get(0)); // Esto seleccionará el primer rol
            }
            
            if (!tipoPromociones.isEmpty()) {
                cbTipoPromocion.setValue(tipoPromociones.get(0)); // Esto seleccionará el primer rol
            }
            
            if (!categorias.isEmpty()) {
                cbCategorias.setValue(categorias.get(0)); // Esto seleccionará el primer rol
            }
            
            dpFechaInicio.setValue(LocalDate.now());
            dpFechaTermino.setValue(LocalDate.now());
        }

    }

    private void cargarDatos(Promocion promocion, boolean isEdicion) {

        if (isEdicion) {
            habilitarComponentes(isEdicion);
            rellenarInputs(promocion);
        } else {
            habilitarComponentes(isEdicion);
        }

    }

    private void rellenarInputs(Promocion promocion) {

        String fechaInicio = promocion.getFechaInicioPromocion();
        LocalDate fechaStart = LocalDate.parse(fechaInicio);

        String fechaTermino = promocion.getFechaTerminoPromocion();
        LocalDate fechaEnd = LocalDate.parse(fechaTermino);

        tfNombre.setText(promocion.getNombrePromocion());
        tfDesc.setText(promocion.getDescripcion());
        dpFechaInicio.setValue(fechaStart);
        dpFechaTermino.setValue(fechaEnd);
        tfRestricciones.setText(promocion.getRestricciones());

        tfPorcentajeCosto.setText(promocion.getPorcentaje_Costo().toString());

        tfCupones.setText("" + promocion.getCuponesMaximos());
        tfCodigoPromo.setText(promocion.getCodigoPromocion());

    }

    private void habilitarComponentes(boolean isEdicion) {

        if (isEdicion) {
            vbBotones.getChildren().clear();
            vbBotones.getChildren().remove(btCancelar);
            vbBotones.getChildren().remove(btGuradarInfo);
            vbBotones.getChildren().add(btEliminar);
            vbBotones.getChildren().add(btEditarPromocion);
            habilitarInputs(false);
            habilitarFoto(true);
        } else {
            vbBotones.getChildren().clear();
            vbBotones.getChildren().add(btCancelar);
            vbBotones.getChildren().add(btGuradarInfo);
            vbBotones.getChildren().remove(btEliminar);
            vbBotones.getChildren().remove(btEditarPromocion);
            habilitarInputs(true);
            habilitarFoto(true);
        }

    }

    private void habilitarInputs(Boolean editable) {
        tfNombre.setEditable(editable);
        tfDesc.setEditable(editable);
        dpFechaInicio.setDisable(!editable);
        dpFechaTermino.setDisable(!editable);
        tfRestricciones.setEditable(editable);
        cbTipoPromocion.setDisable(!editable);
        tfPorcentajeCosto.setEditable(editable);
        cbCategorias.setDisable(!editable);
        tfCupones.setEditable(editable);
        tfCodigoPromo.setEditable(editable);
        cbEmpresas.setDisable(!editable);
        tfEstatus.setEditable(editable);
    }

    private void habilitarFoto(Boolean editable) {
        vbFoto.setDisable(editable);
    }

    private void habilitarFotoEdit(Boolean editable) {
        vbFoto.setDisable(editable);
    }

    @FXML
    private void btEliminarPromocion(ActionEvent event) {

        if (Utilidades.mostrarAlertaConfirmacion("Eliminar promocion", "¿Está seguro de eliminar la promocion?")) {
            Mensaje mensaje = PromocionDAO.eliminarPromocion(promocion);

            if (!mensaje.isError()) {
                Utilidades.mostrarAlertaSimple("Eliminar promocion", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                Utilidades.mostrarAlertaSimple("Eliminar promocion", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            }

        } else {
            Utilidades.mostrarAlertaSimple("Operacion eliminar", "La operación eliminar se ha cancelado", Alert.AlertType.INFORMATION);
        }

        if (moduloPromocionController != null) {
            moduloPromocionController.obtenerPromociones();
        }
    }

    @FXML
    private void btCancelar(ActionEvent event) {

        if (isEdicion) {
            habilitarComponentes(isEdicion);
            cargarDatos(promocion, isEdicion);
            obtenerImagenPromocion(promocion.getIdPromocion());
        } else {
            cerrarVentana();
        }
    }

    @FXML
    private void btSubirInfromacionPromocion(ActionEvent event) {

        if (isEdicion) {
            recuperarDatos();
            editarPromocion(promocion);
        } else {
            recuperarDatos();
            registrarPromocion(promocion);
            cerrarVentana();
        }
    }

    @FXML
    private void btEditarInfromacionPromocion(ActionEvent event) {
        habilitarComponentes(false);
        habilitarFotoEdit(false);
        seleccionarEmpresa(promocion.getIdEmpresa());
        seleccionarCategoria(promocion.getIdCategoria());
        seleccionarTipoPromocion(promocion.getIdTipoPromocion());

    }

    @FXML
    private void btCargarImagen(ActionEvent event) {
        imagenPromo = mostrarDialogoSeleccion();
        if (imagenPromo != null) {
            mostrarImagenSeleccionada(imagenPromo);
        }
    }

    @FXML
    private void btSubirImagen(ActionEvent event) {
        if (imagenPromo != null) {
            Mensaje msj = PromocionDAO.subirImagenPromocion(imagenPromo, promocion.getIdPromocion());
            if (!msj.isError()) {
                Utilidades.mostrarAlertaSimple("Fotografia guardad", msj.getMensaje(), Alert.AlertType.INFORMATION);
            } else {
                Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
            }
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tfNombre.getScene().getWindow();
        stage.close();
    }

    private void cargarEmpresas() {
        empresas = FXCollections.observableArrayList();
        List<Empresa> info = EmpresaDAO.obtenerEmpresas();
        empresas.addAll(info);
        cbEmpresas.setItems(empresas);
    }

    private void seleccionarEmpresa(int idEmpresa) {

        for (Empresa empresa : cbEmpresas.getItems()) {

            if (empresa.getIdEmpresa() == idEmpresa) {

                cbEmpresas.getSelectionModel().select(empresa);
                break;
            }
        }
    }

    private void cargarCategorias() {
        categorias = FXCollections.observableArrayList();
        List<Categoria> info = CategoriasDAO.obtenerCategorias();
        categorias.addAll(info);
        cbCategorias.setItems(categorias);
    }

    private void seleccionarCategoria(int idCategoria) {

        for (Categoria categoria : cbCategorias.getItems()) {

            if (categoria.getIdCategoria() == idCategoria) {

                cbCategorias.getSelectionModel().select(categoria);
                break;
            }
        }
    }

    private void cargarTiposPromociones() {
        tipoPromociones = FXCollections.observableArrayList();
        List<TipoPromocion> info = PromocionDAO.obtenerTiposPromociones();
        tipoPromociones.addAll(info);
        cbTipoPromocion.setItems(tipoPromociones);
    }

    private void seleccionarTipoPromocion(int idTipoPromocion) {

        for (TipoPromocion tipoPromocion : cbTipoPromocion.getItems()) {

            if (tipoPromocion.getIdTipoPromocion() == idTipoPromocion) {

                cbTipoPromocion.getSelectionModel().select(tipoPromocion);
                break;
            }
        }
    }

    private void recuperarDatos() {
        promocion.setNombrePromocion(tfNombre.getText());
        promocion.setDescripcion(tfDesc.getText());
        promocion.setFechaInicioPromocion(dpFechaInicio.getValue().toString());
        promocion.setFechaTerminoPromocion(dpFechaTermino.getValue().toString());
        promocion.setRestricciones(tfRestricciones.getText());
        TipoPromocion tipoPromocionSeleccion = tipoPromociones.get(cbTipoPromocion.getSelectionModel().getSelectedIndex());
        promocion.setIdTipoPromocion(tipoPromocionSeleccion.getIdTipoPromocion());
        promocion.setPorcentaje_Costo(Integer.parseInt(tfPorcentajeCosto.getText()));
        Categoria categoriaSeleccion = categorias.get(cbCategorias.getSelectionModel().getSelectedIndex());
        promocion.setIdCategoria(categoriaSeleccion.getIdCategoria());
        promocion.setCuponesMaximos(Integer.parseInt(tfCupones.getText()));
        promocion.setCodigoPromocion(tfCodigoPromo.getText());
        Empresa empresaSeleccion = empresas.get(cbEmpresas.getSelectionModel().getSelectedIndex());
        promocion.setIdEmpresa(empresaSeleccion.getIdEmpresa());

        String estatusTF = tfEstatus.getText();

        if (estatusTF.equals("Activo")) {
            isEstatus = true;
        }

        if (estatusTF.equals("Inactivo")) {
            isEstatus = false;
        }
        promocion.setEstatus(isEstatus);
        System.out.println(isEstatus);
    }

    private void registrarPromocion(Promocion promocion) {
        Mensaje mensaje = PromocionDAO.registrarPromocion(promocion);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Promocion guardada", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al registrar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarPromocion(Promocion promocion) {
        Mensaje mensaje = PromocionDAO.editarPromocion(promocion);
        if (!mensaje.isError()) {
            Utilidades.mostrarAlertaSimple("Promocion editada", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Utilidades.mostrarAlertaSimple("Error al editar", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void obtenerImagenPromocion(int idPromocion) {

        Promocion promocionImagen = PromocionDAO.obtenerImagenPromocion(idPromocion);
        if (promocionImagen != null && promocionImagen.getImagenPromocionBase64() != null && promocionImagen.getImagenPromocionBase64().length() > 0) {
            byte[] decodeImg = Base64.getDecoder().decode(promocionImagen.getImagenPromocionBase64().replaceAll("\\n", ""));
            Image image = new Image(new ByteArrayInputStream(decodeImg));
            ivImagenPromocion.setImage(image);
        }

    }

    private void mostrarImagenSeleccionada(File fotoFile) {

        try {

            BufferedImage bufferedImage = ImageIO.read(fotoFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ivImagenPromocion.setImage(image);

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
