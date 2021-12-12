/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional;

import gestor_de_archivos_funcional.Funciones.constantes;
import gestor_de_archivos_funcional.Funciones.funciones;
import gestor_de_archivos_funcional.Main.FXMLMainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos_funcional.class.getResource("Main/FXMLMain.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLMainController fXMLMainControllerInstancia = fxmlLoader.getController();
        fXMLMainControllerInstancia.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(constantes.TITULO_MAIN);
        funciones.crear_carpeta_principal(); //Crea la carpeta si no existe
        stage.show();
        stage.setOnCloseRequest(a -> Platform.exit());
    }

}
