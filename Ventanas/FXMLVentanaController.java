/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Ventanas;

import gestor_de_archivos_funcional.Funciones.constantes;
import gestor_de_archivos_funcional.Funciones.funciones;
import gestor_de_archivos_funcional.Funciones.ventanaAcciones;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dangilcal
 */
public class FXMLVentanaController implements Initializable {

    String creacion;
    @FXML
    private Label label;

    @FXML
    private Button create;

    @FXML
    private TextField tField;

    private TextArea textArea;
    private TilePane tilepane;
    private Stage stage_main;

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public void setTextTilepane(TilePane tilePane) {
        this.tilepane = tilePane;
    }

    public void setStageMain(Stage stage) {
        this.stage_main = stage;
    }

    @FXML
    private void onKeyTyped(KeyEvent event) {
        if (event.getCharacter().matches("[0-9]")) {
            event.consume(); //Elimina los numeros
        }
    }

    @FXML
    private void onKeyReleased(KeyEvent event) {
        if (tField.getText().isEmpty()) {
            create.setDisable(true); //Habilita el boton "Create"
        } else {
            create.setDisable(false);//Deshabilita el boton "Create"
        }
    }

    @FXML
    private void onMouseClickedClose(MouseEvent event) {
        //Cierra la ventana
        funciones.cerrar_ventana(event);
    }

    //Pulsar el boton de crear
    @FXML
    private void onMouseClickedCreate(MouseEvent event) throws IOException {
        String nombre = tField.getText(); //Recoge el texto
        if (creacion.equals(constantes.CREAR_FICHERO)) {
            ventanaAcciones.crear_fichero(nombre, textArea, tilepane, stage_main); //Crea fichero
        } else if (creacion.equals(constantes.CREAR_DIRECTORIO)) {
            ventanaAcciones.crear_directorio(nombre, textArea, tilepane, stage_main); //Crea directorio
        }
        //cierra la ventana
        funciones.cerrar_ventana(event);
    }

    @FXML
    public void enviarLabel(String texto) {
        label.setText(texto); //Cambia el nombre del label a crear fichero o crear directorio
        creacion = texto; //Guarda el nombre del label para seleccionar que quieres crear
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
