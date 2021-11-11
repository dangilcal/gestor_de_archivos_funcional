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
import java.util.ArrayList;
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
import javafx.stage.Stage;

/**
 *
 * @author dangilcal
 */
public class ventanaAcciones {

    static String rutaActual = constantes.CARPETA_RAIZ;
    static String rutaGuardada = constantes.CARPETA_RAIZ;
    static String nombre_fichero;
    static String name;

    public static void rutaAcual_raiz() {
        rutaActual = constantes.CARPETA_RAIZ;
    }

    public static void mostrar(TilePane tilepane, TextArea textArea, Stage stage_main, Stage stage) throws IOException {
        List<String> rutas = new ArrayList();
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
                Entrar_carpeta(caja, file, textArea, tilepane, stage_main, stage); //Doble click para entrar en la carpeta
            } else if (file.isFile()) {
                foto = constantes.IMAGEN_ARCHIVO;

                Editar_fichero(caja, file, textArea, stage_main, stage);
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

    private static void Entrar_carpeta(BorderPane caja, File file, TextArea textArea, TilePane tilepane, Stage stage_main, Stage stage) {
        caja.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                stage.setTitle(file.getName());
                rutaActual = rutaGuardada + "/" + file.getName();
                try {
                    mostrar(tilepane, textArea, stage_main, stage);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private static void Editar_fichero(BorderPane caja, File file, TextArea textArea, Stage stage_main, Stage stage) {
        caja.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                nombre_fichero = file.getName();
                rutaActual = rutaGuardada + "/" + nombre_fichero;
                stage_main.setTitle(nombre_fichero);
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get(rutaActual));
                    String content = new String(bytes, StandardCharsets.UTF_8);
                    textArea.setText(content);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.close();
            }
        }
        );
    }

    public static void crear_directorio(String nombre, TextArea textArea, TilePane tilepane, Stage stage) throws IOException {
        Path path = Paths.get(rutaGuardada + "/" + nombre);
        try {
            Files.createDirectory(path);
            rutaActual = rutaGuardada;
        } catch (IOException ex) {
            System.out.println("Ya existe la carpeta");
        }
        //mostrar(tilepane, textArea, stage_main); //Limpia y muestra los ficheros
    }

    public static void crear_fichero(String nombre, TextArea textArea, TilePane tilepane, Stage stage) throws IOException {
        Path path = Paths.get(rutaGuardada + "/" + nombre + ".txt");
        name = nombre;
        try {
            Files.createFile(path);
            rutaActual = rutaGuardada;
        } catch (IOException ex) {
            System.out.println("Ya existe el archivo");
        }
        if (tilepane != null) {
            //mostrar(tilepane, textArea, stage_main); //Limpia y muestra los ficheros
        }
    }

    public static void eliminar_fichero(Stage stage) {
        File file = new File(rutaActual);
        file.delete();
        rutaActual = constantes.CARPETA_RAIZ;
        stage.setTitle(constantes.TITULO_MAIN);

    }

    public static void guardar_fichero(TextArea textArea, Stage stage_main) throws IOException {
        if (stage_main.getTitle().equals(constantes.TITULO_MAIN)) {
            funciones.ventana_crear_fichero_directorio(constantes.CREAR_FICHERO, textArea, null);

            Modificar_fichero(constantes.CARPETA_RAIZ + "/" + name, textArea);
        } else {
            Modificar_fichero(rutaActual, textArea);
        }
        rutaActual = constantes.CARPETA_RAIZ;

    }

    public static void CreateTilePaneWIcons(TilePane titlePane, TextArea textArea, Stage stage_main, Stage stage) throws IOException {

        funciones.crear_carpeta_principal(); //Crea la carpeta si no existe
        ventanaAcciones.mostrar(titlePane, textArea, stage_main, stage); //Muestra el panel con los ficheros

    }

    public static void Modificar_fichero(String rut, TextArea textArea) throws IOException {
        String text = textArea.getText();
        Files.write(Paths.get(rut), text.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}
