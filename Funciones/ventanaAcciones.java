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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    static String rutaActual = constantes.CARPETA_RAIZ;
    static String rutaGuardada = rutaActual;
    //static String nombre_fichero;

    public static void mostrar(TilePane tp) throws IOException {
        List<Path> result;
        try (Stream<Path> paths = Files.walk(Paths.get(rutaActual), 1)) {
            result = paths.collect(Collectors.toList()); //Guarda todos los ficheros en result
        }
        titlepane = tp;
        tp.getChildren().clear(); //Limpia la pantalla

        //Creacion de variables
        String foto = null;

        //Insertar iconos
        for (int i = 1; i < result.size(); i++) { //Recorre cada fichero
            ImageView imageView = new ImageView();
            BorderPane caja = new BorderPane();
            Text nombre_archivo = new Text();
            BorderPane.setAlignment(nombre_archivo, Pos.CENTER);
            caja.setPadding(new Insets(10, 10, 10, 10));    //Padding para tener mas espacio entre los iconos
            File file = result.get(i).toFile();
            if (file.isDirectory()) { //Elige la imagen
                foto = constantes.IMAGEN_CARPETA;
                Entrar_carpeta(caja, file); //Doble click para entrar en la carpeta
            } else if (file.isFile()) {
                foto = constantes.IMAGEN_ARCHIVO;

                caja.setOnMouseClicked((event) -> {
                    if (event.getClickCount() == 2) {
                        funciones.stage_mostrar_ficheros.close();
                        rutaActual = rutaGuardada + "/" + file.getName();
                        funciones.stage_mostrar_info.setTitle(file.getName());
                    }
                });
            }
            //Insertar imagen y el tamaño de las imagenes
            Image imagen = new Image(gestor_de_archivos_funcional.Gestor_de_archivos_funcional.class.getResource(foto).toString());
            imageView.setImage(imagen);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            caja.setCenter(imageView);
            //Insertar texto centrado
            nombre_archivo.setText(file.getName());
            caja.setBottom(nombre_archivo); //Centra en texto
            //Mostrar todo en TilePane
            tp.getChildren().addAll(caja);
        }
        rutaGuardada = rutaActual;
        rutaActual = constantes.CARPETA_RAIZ;

    }

    private static void Entrar_carpeta(BorderPane caja, File file) {
        caja.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                funciones.stage_mostrar_ficheros.setTitle(file.getName());
                rutaActual = rutaGuardada + "/" + file.getName();
                try {
                    mostrar(titlepane);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void crear_directorio(String nombre) throws IOException {
        Path path = Paths.get(rutaGuardada + "/" + nombre);
        try {
            Files.createDirectory(path);
            rutaActual = rutaGuardada;
        } catch (IOException ex) {
            System.out.println("Ya existe la carpeta");
        }
        mostrar(titlepane); //Limpia y muestra los ficheros
    }

    public static void crear_fichero(String nombre) throws IOException {
        Path path = Paths.get(rutaGuardada + "/" + nombre + ".txt");
        try {
            Files.createFile(path);
            rutaActual = rutaGuardada;
        } catch (IOException ex) {
            System.out.println("Ya existe el archivo");
        }

        mostrar(titlepane); //Limpia y muestra los ficheros
    }

}
