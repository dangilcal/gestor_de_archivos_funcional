/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Abrir;

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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author dangilcal
 */
public class FXMLAbrirController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TilePane titlePane;

//    public static void coloca(BorderPane caja) {
//        //titlePane.getChildren().add(caja);
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            funciones.crear_carpeta_principal();

            funciones.mostrar(titlePane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ContextMenu contextMenu = new ContextMenu();
        MenuItem crear_archivo = new MenuItem("Crear archivo");
        MenuItem crear_carpeta = new MenuItem("Crear carpeta");
        contextMenu.getItems().addAll(crear_archivo, crear_carpeta);

        titlePane.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(titlePane, e.getScreenX(), e.getScreenY());
        });

        titlePane.setOnMousePressed((event) -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
        });

        crear_archivo.setOnAction(event -> {
            try {
                funciones.crear_ventana("Crear archivo");
            } catch (IOException ex) {
                Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        crear_carpeta.setOnAction(event -> {
            try {
                funciones.crear_ventana("Crear carpeta");
            } catch (IOException ex) {
                Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
