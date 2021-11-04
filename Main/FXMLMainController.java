/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Main;

import gestor_de_archivos_funcional.Funciones.funciones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;

/**
 *
 * @author dangilcal
 */
public class FXMLMainController implements Initializable {

    @FXML
    private Menu Menu_Info;

    @FXML
    private void click_info(ActionEvent k) {
        funciones.ventana_mostrar_info();
    }

    @FXML
    private void click_abrir(ActionEvent k) {
        funciones.ventana_mostrar_ficheros();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
