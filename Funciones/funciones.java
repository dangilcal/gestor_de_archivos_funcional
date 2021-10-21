/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Funciones;

import gestor_de_archivos_funcional.Abrir.FXMLAbrirController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 *
 * @author dangilcal
 */
public class funciones {

    public static void crear() {
        Path path = Paths.get("FILES");
        try {
            Files.createDirectory(path);
        } catch (IOException ex) {
            System.out.println("Ya existe la carpeta");
        }
    }

    public static void mostrar() throws IOException {
        List<Path> result;
        try (Stream<Path> paths = Files.walk(Paths.get("FILES"))) {
            result = paths.collect(Collectors.toList());
        }
        //Creacion de variables
        ImageView imageView = new ImageView();
        BorderPane caja = new BorderPane();
        Text nombre_fichero = new Text();
        File file;
        String foto;

        //Insertar iconos
        for (int i = 0; i < result.size(); i++) {
            file = result.get(i).toFile();
            if (!file.getName().equals("FILES")) {
                if (file.isDirectory()) {
                    foto = "Resources/carpeta.png";
                } else {
                    foto = "Resources/fichero.png";
                }
                Image imagen = new Image(gestor_de_archivos_funcional.Gestor_de_archivos_funcional.class.getResource(foto).toString());
                imageView.setImage(imagen);
                imageView.setFitHeight(80);
                imageView.setFitWidth(80);
                caja.setCenter(imageView);
                nombre_fichero.setText(file.getName());
                caja.setBottom(nombre_fichero);
                FXMLAbrirController.muestra(caja);
            }

        }

    }

}
