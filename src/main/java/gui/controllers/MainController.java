package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    private Stage primaryStage; // Referencia al Stage principal

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void abrirCargarDatos() {
        cargarVista("/fxml/CargarDatosView.fxml", "Cargar Datos");
    }

    @FXML
    private void abrirVerDatos() {
        cargarVista("/fxml/VerDatosView.fxml", "Ver Datos");
    }

    @FXML
    private void salir() {
        primaryStage.close(); // Cierra la ventana principal correctamente
    }

    private void cargarVista(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle(titulo);
            newStage.initOwner(primaryStage);

            // Opcional: Configura tamaño mínimo
            newStage.setMinWidth(800);
            newStage.setMinHeight(600);

            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cargar vista:\n" + e.getMessage());

            // Debug adicional
            System.err.println("Ruta intentada: " + getClass().getResource(fxmlPath));
        }
    }

    private void mostrarError(String mensaje) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR
        );
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

