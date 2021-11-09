/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Abrir;

import gestor_de_archivos_funcional.Funciones.constantes;
import gestor_de_archivos_funcional.Funciones.funciones;
import gestor_de_archivos_funcional.Funciones.ventanaAcciones;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author dangilcal
 */
public class FXMLAbrirController implements Initializable {

    @FXML
    private TilePane titlePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            funciones.crear_carpeta_principal(); //Crea la carpeta si no existe

            ventanaAcciones.mostrar(titlePane); //Muestra el panel con los ficheros
        } catch (IOException ex) {
            Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ContextMenu contextMenu = new ContextMenu();    //Crea un menu
        MenuItem crear_archivo = new MenuItem(constantes.CREAR_FICHERO); //Añades crear fichero al menu
        MenuItem crear_carpeta = new MenuItem(constantes.CREAR_DIRECTORIO); //Añades crear directorio al menu
        contextMenu.getItems().addAll(crear_archivo, crear_carpeta);

        //Al pulsar click derecho en el titlePane muestra el menu
        titlePane.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(titlePane, e.getScreenX(), e.getScreenY());
        });

        //Si pulsas con click izquierdo en el titlePane dejas de mostrar el menu
        titlePane.setOnMousePressed((event) -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
        });

        //Al pulsar crear archivo ejecuta la función
        crear_archivo.setOnAction(event -> {
            try {
                funciones.ventana_crear_fichero_directorio(constantes.CREAR_FICHERO);
            } catch (IOException ex) {
                Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //Al pulsar crear directorio ejecuta la función
        crear_carpeta.setOnAction(event -> {
            try {
                funciones.ventana_crear_fichero_directorio(constantes.CREAR_DIRECTORIO);
            } catch (IOException ex) {
                Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
