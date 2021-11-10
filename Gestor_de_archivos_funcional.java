/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional;

import gestor_de_archivos_funcional.Funciones.funciones;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author dangilcal
 */
public class Gestor_de_archivos_funcional extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        funciones.ventana_main(stage);
    }

}
