/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Abrir;

import gestor_de_archivos_funcional.Funciones.constantes;
import gestor_de_archivos_funcional.Funciones.funciones;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author dangilcal
 */
public class FXMLAbrirController implements Initializable {

    @FXML
    private TilePane tilePane;

    private TextArea textArea;

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ContextMenu contextMenu = new ContextMenu();    //Crea un menu
        MenuItem crear_archivo = new MenuItem(constantes.CREAR_FICHERO); //Añades crear fichero al menu
        MenuItem crear_carpeta = new MenuItem(constantes.CREAR_DIRECTORIO); //Añades crear directorio al menu
        contextMenu.getItems().addAll(crear_archivo, crear_carpeta);

        //Al pulsar click derecho en el titlePane muestra el menu
        tilePane.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(tilePane, e.getScreenX(), e.getScreenY());
        });

        //Si pulsas con click izquierdo en el titlePane dejas de mostrar el menu
        tilePane.setOnMousePressed((event) -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
        });

        //Al pulsar crear archivo ejecuta la función
        crear_archivo.setOnAction(event -> {
            try {
                funciones.ventana_crear_fichero_directorio(constantes.CREAR_FICHERO, textArea);
            } catch (IOException ex) {
                Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //Al pulsar crear directorio ejecuta la función
        crear_carpeta.setOnAction(event -> {
            try {
                funciones.ventana_crear_fichero_directorio(constantes.CREAR_DIRECTORIO, textArea);
            } catch (IOException ex) {
                Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
