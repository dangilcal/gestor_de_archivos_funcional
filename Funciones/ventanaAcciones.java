/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Funciones;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
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

    //static TilePane tilepane;
    static String rutaActual = constantes.CARPETA_RAIZ;
    static String rutaGuardada = constantes.CARPETA_RAIZ;
    static String nombre_fichero;
    static String name;

    public static void rutaAcual_raiz() {
        rutaActual = constantes.CARPETA_RAIZ;
    }

    public static void mostrar(TilePane tilepane, TextArea textArea) throws IOException {
        List<Path> result;
        try (Stream<Path> paths = Files.walk(Paths.get(rutaActual), 1)) {
            result = paths.collect(Collectors.toList()); //Guarda todos los ficheros en result
        }
        //tilepane = tp;
        tilepane.getChildren().clear(); //Limpia la pantalla

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
                Entrar_carpeta(caja, file, textArea, tilepane); //Doble click para entrar en la carpeta
            } else if (file.isFile()) {
                foto = constantes.IMAGEN_ARCHIVO;

                Editar_fichero(caja, file, textArea);
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
            tilepane.getChildren().addAll(caja);
        }
        rutaGuardada = rutaActual;
        rutaActual = constantes.CARPETA_RAIZ;

    }

    private static void Entrar_carpeta(BorderPane caja, File file, TextArea textArea, TilePane tilepane) {
        caja.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                funciones.stage_mostrar_ficheros.setTitle(file.getName());
                rutaActual = rutaGuardada + "/" + file.getName();
                try {
                    mostrar(tilepane, textArea);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private static void Editar_fichero(BorderPane caja, File file, TextArea textArea) {
        caja.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                nombre_fichero = file.getName();
                rutaActual = rutaGuardada + "/" + nombre_fichero;
                funciones.stage_main.setTitle(nombre_fichero);
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get(rutaActual));
                    String content = new String(bytes, StandardCharsets.UTF_8);
                    textArea.setText(content);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                funciones.stage_mostrar_ficheros.close();
            }
        }
        );
    }

    public static void crear_directorio(String nombre, TextArea textArea, TilePane tilepane) throws IOException {
        Path path = Paths.get(rutaGuardada + "/" + nombre);
        try {
            Files.createDirectory(path);
            rutaActual = rutaGuardada;
        } catch (IOException ex) {
            System.out.println("Ya existe la carpeta");
        }
        mostrar(tilepane, textArea); //Limpia y muestra los ficheros
    }

    public static void crear_fichero(String nombre, TextArea textArea, TilePane tilepane) throws IOException {
        Path path = Paths.get(rutaGuardada + "/" + nombre + ".txt");
        name = nombre;
        try {
            Files.createFile(path);
            rutaActual = rutaGuardada;
        } catch (IOException ex) {
            System.out.println("Ya existe el archivo");
        }
        if (tilepane != null) {
            mostrar(tilepane, textArea); //Limpia y muestra los ficheros
        }
    }

    public static void eliminar_fichero() {
        File file = new File(rutaActual);
        file.delete();
        rutaActual = constantes.CARPETA_RAIZ;
        funciones.stage_main.setTitle(constantes.TITULO_MAIN);

    }

    public static void guardar_fichero(TextArea textArea) throws IOException {
        if (funciones.stage_main.getTitle().equals(constantes.TITULO_MAIN)) {
            funciones.ventana_crear_fichero_directorio(constantes.CREAR_FICHERO, textArea);

            Modificar_fichero(constantes.CARPETA_RAIZ + "/" + name, textArea);
        } else {
            Modificar_fichero(rutaActual, textArea);
        }
        rutaActual = constantes.CARPETA_RAIZ;

    }

    public static void CreateTilePaneWIcons(TilePane titlePane, TextArea textArea) throws IOException {

        funciones.crear_carpeta_principal(); //Crea la carpeta si no existe
        ventanaAcciones.mostrar(titlePane, textArea); //Muestra el panel con los ficheros

    }

    public static void Modificar_fichero(String rut, TextArea textArea) throws IOException {
        String text = textArea.getText();
        Files.write(Paths.get(rut), text.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}
