/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dangilcal
 */
public class FXMLVentanaController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button create;

    @FXML
    private TextField tField;

    @FXML
    private void onKeyReleased(KeyEvent event) {
        if (event.getCharacter().matches("[0-9]")) {
            event.consume();
        }
        if (tField.getText().isEmpty()) {
            create.setDisable(true);
        } else {
            create.setDisable(false);
        }
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void enviarLabel(String texto) {
        label.setText(texto);
    }

}
