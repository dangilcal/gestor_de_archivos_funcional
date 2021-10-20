/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Main;

import gestor_de_archivos_funcional.Funciones.funciones;
import gestor_de_archivos_funcional.Gestor_de_archivos_funcional;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author dangilcal
 */
public class FXMLMainController implements Initializable {

    @FXML
    private Menu Menu_Info;

    @FXML
    private void click_info(ActionEvent k) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos_funcional.class.getResource("Info/FXMLInfo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Info");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void click_abrir(ActionEvent k) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos_funcional.class.getResource("Abrir/FXMLAbrir.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Abrir");
            stage.setScene(new Scene(root));
            funciones.crear();
            funciones.mostrar();
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
