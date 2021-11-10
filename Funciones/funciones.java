/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Funciones;

import gestor_de_archivos_funcional.Abrir.FXMLAbrirController;
import gestor_de_archivos_funcional.Gestor_de_archivos_funcional;
import gestor_de_archivos_funcional.Ventanas.FXMLVentanaController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author dangilcal
 */
public class funciones {

    funciones funciones;
    static Stage stage_mostrar_ficheros;
    static Stage stage_main;

    public static void ventana_main(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Gestor_de_archivos_funcional.class.getResource("Main/FXMLMain.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage_main = stage;
        stage.setTitle(constantes.TITULO_MAIN);
        stage.show();
        stage.setOnCloseRequest(a -> Platform.exit());
    }

    public static void crear_carpeta_principal() { //Crea la carpeta principal del proyecto si no existe
        Path path = Paths.get(constantes.CARPETA_RAIZ); //Dentro se encuentra la ruta y nombre de la carperta raiz
        try {
            Files.createDirectory(path); //Crear directorio
        } catch (IOException ex) {
            System.out.println("Ya existe la carpeta");
        }
    }

    public static void ventana_mostrar_info() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos_funcional.class.getResource("Info/FXMLInfo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL); //Window modal permite interactuar con el main
            stage.setTitle("Info"); //Nombre de la ventgana
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void ventana_mostrar_ficheros(TextArea textArea) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos_funcional.class.getResource("Abrir/FXMLAbrir.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            FXMLAbrirController fXMLAbrirControllerInstancia = fxmlLoader.getController();
            Stage stage = new Stage();
            fXMLAbrirControllerInstancia.setTextArea(textArea);
            ventanaAcciones.CreateTilePaneWIcons(fXMLAbrirControllerInstancia.getTilePane(), textArea);
            stage.initModality(Modality.APPLICATION_MODAL); //Application modal no permite interactuar con otras pantallas
            stage.setTitle("FILES"); //Nombre de la ventana
            stage_mostrar_ficheros = stage;
            stage.setScene(new Scene(root));
            //ventanaAcciones.rutaAcual_raiz();
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void ventana_crear_fichero_directorio(String nombreLabel, TextArea textArea) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos_funcional.class.getResource("Ventanas/FXMLVentana.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        //Crea una instancia
        FXMLVentanaController fXMLVentanaControllerInstancia = fxmlLoader.getController();
        fXMLVentanaControllerInstancia.enviarLabel(nombreLabel); //Modifica el label con crear carpeta o archivo
        fXMLVentanaControllerInstancia.setTextArea(textArea);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);//Application modal no permite interactuar con otras pantallas
        stage.setTitle(nombreLabel.toUpperCase()); //Nombre de la ventana en mayusculas
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void cerrar_ventana(MouseEvent event) {
        //Cierra la ventana
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
