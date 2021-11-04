/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Funciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

/**
 *
 * @author dangilcal
 */
public class ventanaAcciones {

    static TilePane titlepane;
    static String ruta = "FILES/";

    public static void mostrar(TilePane tp) throws IOException {
        List<Path> result;
        try (Stream<Path> paths = Files.walk(Paths.get(ruta))) {
            result = paths.collect(Collectors.toList()); //Guarda todos los ficheros en result
        }
        titlepane = tp;
        tp.getChildren().clear(); //Limpia la pantalla

        //Creacion de variables
        File file;
        String foto = null;

        //Insertar iconos
        for (int i = 1; i < result.size(); i++) { //Recorre cada fichero
            ImageView imageView = new ImageView();
            BorderPane caja = new BorderPane();
            Text nombre_fichero = new Text();
            BorderPane.setAlignment(nombre_fichero, Pos.CENTER);
            caja.setPadding(new Insets(10, 10, 10, 10));    //Padding para tener mas espacio entre los iconos
            file = result.get(i).toFile();
            if (file.isDirectory()) { //Elige la imagen
                foto = constantes.IMAGEN_CARPETA;
            } else if (file.isFile()) {
                foto = constantes.IMAGEN_ARCHIVO;
            }
            //Insertar imagen y el tamaño de las imagenes
            Image imagen = new Image(gestor_de_archivos_funcional.Gestor_de_archivos_funcional.class.getResource(foto).toString());
            imageView.setImage(imagen);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            caja.setCenter(imageView);
            //Insertar texto centrado
            nombre_fichero.setText(file.getName());
            caja.setBottom(nombre_fichero); //Centra en texto
            //Mostrar todo en TilePane
            tp.getChildren().addAll(caja);

        }

    }

    public static void crear_directorio(String nombre) throws IOException {
        Path path = Paths.get(ruta + nombre);
        try {
            Files.createDirectory(path);
        } catch (IOException ex) {
            System.out.println("Ya existe la carpeta");
        }
        mostrar(titlepane); //Limpia y muestra los ficheros
    }

    public static void crear_fichero(String nombre) throws IOException {
        Path path = Paths.get("FILES/" + nombre + ".txt");
        try {
            Files.createFile(path);
        } catch (IOException ex) {
            System.out.println("Ya existe el archivo");
        }

        mostrar(titlepane); //Limpia y muestra los ficheros
    }

}
