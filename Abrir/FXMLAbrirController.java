/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Abrir;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

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
    static TitledPane titlePane;

    public static void muestra(BorderPane caja) {
        titlePane.getChildrenUnmodifiable(); //No sale Children
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
