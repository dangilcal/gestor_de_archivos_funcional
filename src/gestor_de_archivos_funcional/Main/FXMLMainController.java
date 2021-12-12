/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Main;

import gestor_de_archivos_funcional.Funciones.SingletonRutas;
import gestor_de_archivos_funcional.Funciones.constantes;
import gestor_de_archivos_funcional.Funciones.funciones;
import gestor_de_archivos_funcional.Funciones.ventanaAcciones;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author dangilcal
 */
public class FXMLMainController implements Initializable {

    private Stage stage;

    @FXML
    private Menu Menu_Info;

    @FXML
    private TextArea textArea;

    @FXML
    private void click_info(ActionEvent k) {
        funciones.ventana_mostrar_info();
    }

    @FXML
    private void click_abrir(ActionEvent k) {
        SingletonRutas sin = SingletonRutas.getInstancia();
        sin.setResetRuta();
        textArea.setText("");
        stage.setTitle(constantes.TITULO_MAIN);
        funciones.ventana_mostrar_ficheros(textArea, stage);
    }

    @FXML
    private void click_borrar(ActionEvent k) {
        ventanaAcciones.eliminar_fichero(stage);
        textArea.setText("");
    }

    @FXML
    private void click_guardar(ActionEvent k) throws IOException {
        ventanaAcciones.guardar_fichero(textArea, stage, null);
    }

    @FXML
    private void click_new_file(ActionEvent k) {
        textArea.setText("");
        stage.setTitle(constantes.TITULO_MAIN);
    }

    public void setStage(Stage s) {
        stage = s;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
