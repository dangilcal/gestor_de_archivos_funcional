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
            funciones.crear();

            funciones.mostrar(titlePane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAbrirController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
