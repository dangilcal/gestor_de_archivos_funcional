/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Funciones;

import gestor_de_archivos_funcional.Gestor_de_archivos_funcional;
import gestor_de_archivos_funcional.Ventanas.FXMLVentanaController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author dangilcal
 */
public class funciones {

    funciones funciones;
    static TilePane titlepane;

    public static void crear_carpeta_principal() {
        Path path = Paths.get("FILES");
        try {
            Files.createDirectory(path);
        } catch (IOException ex) {
            System.out.println("Ya existe la carpeta");
        }
    }

    public static void mostrar(TilePane tp) throws IOException {
        List<Path> result;
        try (Stream<Path> paths = Files.walk(Paths.get("FILES"))) {
            result = paths.collect(Collectors.toList());
        }
        titlepane = tp;
        tp.getChildren().clear();

        //Creacion de variables
        File file;
        String foto = null;

        //Insertar iconos
        for (int i = 1; i < result.size(); i++) {
            ImageView imageView = new ImageView();
            BorderPane caja = new BorderPane();
            Text nombre_fichero = new Text();
            BorderPane.setAlignment(nombre_fichero, Pos.CENTER);
            caja.setPadding(new Insets(10, 10, 10, 10));
            file = result.get(i).toFile();
            if (file.isDirectory()) {
                foto = "Resources/carpeta.png";
            } else if (file.isFile()) {
                foto = "Resources/fichero.png";
            }
            Image imagen = new Image(gestor_de_archivos_funcional.Gestor_de_archivos_funcional.class.getResource(foto).toString());
            imageView.setImage(imagen);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            caja.setCenter(imageView);
            nombre_fichero.setText(file.getName());
            caja.setBottom(nombre_fichero);
            tp.getChildren().addAll(caja);

        }

    }

    public static void crear_ventana(String i) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos_funcional.class.getResource("Ventanas/FXMLVentana.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        gestor_de_archivos_funcional.Ventanas.FXMLVentanaController FXMLVentanaControllerInstancia = (FXMLVentanaController) fxmlLoader.getController();
        FXMLVentanaControllerInstancia.enviarLabel(i);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(i.toUpperCase());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void crear_directorio(String nombre) throws IOException {
        Path path = Paths.get("FILES/" + nombre);
        try {
            Files.createDirectory(path);
        } catch (IOException ex) {
            System.out.println("Ya existe la carpeta");
        }
        mostrar(titlepane);
    }

    public static void crear_fichero(String nombre) throws IOException {
        Path path = Paths.get("FILES/" + nombre + ".txt");
        Files.createFile(path);
        mostrar(titlepane);
    }

}
