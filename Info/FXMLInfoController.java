/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Info;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author dangilcal
 */
public class FXMLInfoController implements Initializable {

    @FXML
    private ImageView image_view;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            start();
        } catch (Exception ex) {
            Logger.getLogger(FXMLInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() throws Exception {
        //Imagen responsiva
        image_view.fitWidthProperty().bind(pane.widthProperty());
        image_view.fitHeightProperty().bind(pane.heightProperty());
    }

}
