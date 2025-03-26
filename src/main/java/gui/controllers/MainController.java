package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private void abrirCargarDatos() throws Exception {
        cargarVista("/fxml/CargarDatosView.fxml", "Cargar Datos");
    }

    @FXML
    private void abrirVerDatos() throws Exception {
        cargarVista("/fxml/VerDatosView.fxml", "Ver Datos");
    }

    @FXML
    private void salir() {
        System.exit(0);
    }

    private void cargarVista(String fxmlPath, String titulo) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        stage.show();
    }
}