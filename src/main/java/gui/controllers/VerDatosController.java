package gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.ControladoraLogica;
import logic.Mascota;

import java.util.List;

public class VerDatosController {

    @FXML
    private TableView<Mascota> tablaMascotas;
    @FXML
    private TableColumn<Mascota, Integer> numClienteColumn;
    @FXML
    private TableColumn<Mascota, String> nombreColumn;
    @FXML
    private TableColumn<Mascota, String> razaColumn;
    @FXML
    private TableColumn<Mascota, String> colorColumn;
    @FXML
    private TableColumn<Mascota, String> alergicoColumn;
    @FXML
    private TableColumn<Mascota, String> atencionEspecialColumn;
    @FXML
    private TableColumn<Mascota, String> duenoColumn;
    @FXML
    private TableColumn<Mascota, String> celDuenoColumn;
    @FXML
    private Button eliminarButton;
    @FXML
    private Button modificarButton;

    private final ControladoraLogica controlLogica;

    public VerDatosController() {
        this.controlLogica = new ControladoraLogica();
    }

    @FXML
    public void initialize() {
        numClienteColumn.setCellValueFactory(new PropertyValueFactory<>("num_cliente"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        razaColumn.setCellValueFactory(new PropertyValueFactory<>("raza"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        alergicoColumn.setCellValueFactory(new PropertyValueFactory<>("alergico"));
        atencionEspecialColumn.setCellValueFactory(new PropertyValueFactory<>("atencion_especial"));

        duenoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnDueno() != null ? cellData.getValue().getUnDueno().getNombre() : "Sin dueño"));
        celDuenoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnDueno() != null ? cellData.getValue().getUnDueno().getCelDueno() : "Sin teléfono"));

        cargarDatos();

        eliminarButton.setDisable(true);
        modificarButton.setDisable(true);

        tablaMascotas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean filaSeleccionada = newSelection != null;
            eliminarButton.setDisable(!filaSeleccionada);
            modificarButton.setDisable(!filaSeleccionada);
        });
    }

    private void cargarDatos() {
        List<Mascota> mascotas = controlLogica.listarMascotas();
        ObservableList<Mascota> listaObservable = FXCollections.observableArrayList(mascotas);
        tablaMascotas.setItems(listaObservable);
    }

    public void eliminarFila(ActionEvent actionEvent) {
        // Obtener la fila seleccionada
        Mascota mascotaSeleccionada = tablaMascotas.getSelectionModel().getSelectedItem();

        if (mascotaSeleccionada != null) {
            int id = mascotaSeleccionada.getNum_cliente();

            controlLogica.borrarMascota(id);

            // Mostrar mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Se eliminó correctamente.");
            alert.showAndWait();

            // Refrescar tabla
            cargarDatos();
        }
    }

    public void modificarFila(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModificarDatosView.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Modificar Datos");
            newStage.setMinWidth(800);
            newStage.setMinHeight(600);

            // Obtener el controlador de la ventana Modificar Datos
            ModificarDatosController modificarDatosC = loader.getController();
            // Obtener la fila seleccionada
            Mascota mascotaSeleccionada = tablaMascotas.getSelectionModel().getSelectedItem();
            // Setear el atributo mascota del controlador Modificar Datos
            modificarDatosC.setMascota(mascotaSeleccionada);

            // Cuando se cierre la ventana, recarga los datos
            newStage.setOnHidden(event -> cargarDatos());

            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ruta intentada: " + getClass().getResource("/fxml/ModificarDatosView.fxml"));
        }
    }
}
