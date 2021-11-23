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
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author dangilcal
 */
public class ventanaAcciones {

    public static void mostrar(TilePane tilepane, TextArea textArea, Stage stage_main) throws IOException {
        SingletonRutas sin = SingletonRutas.getInstancia();
        List<Path> result;
        try (Stream<Path> paths = Files.walk(Paths.get(sin.getRuta()), 1)) {
            result = paths.collect(Collectors.toList()); //Guarda todos los ficheros en result
        }
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
                Entrar_carpeta(caja, file, textArea, tilepane, stage_main); //Doble click para entrar en la carpeta
            } else if (file.isFile()) {
                foto = constantes.IMAGEN_ARCHIVO;
                Editar_fichero(caja, file, textArea, tilepane, stage_main);
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

    }

    private static void Entrar_carpeta(BorderPane caja, File file, TextArea textArea, TilePane tilepane, Stage stage_main) {
        ContextMenu contextMenu = new ContextMenu();    //Crea un menu
        MenuItem Delete = new MenuItem(constantes.BORRAR);
        contextMenu.getItems().add(Delete);
        SingletonRutas sin = SingletonRutas.getInstancia();

        caja.setOnMouseClicked((event) -> {

            if (event.getClickCount() == 2) {
                sin.setCarpeta(file.getName());
                funciones.titulo_ventana(event, sin.getRuta());
                try {
                    mostrar(tilepane, textArea, stage_main);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        caja.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(caja, e.getScreenX(), e.getScreenY());
            e.consume();
        });

        Delete.setOnAction(event -> {
            Click_Borrar_carpeta(file, textArea, tilepane, stage_main);
        });
    }

    private static void Click_Borrar_carpeta(File file, TextArea textArea, TilePane tilepane, Stage stage_main) {
        SingletonRutas sin = SingletonRutas.getInstancia();
        Path rootPath = Paths.get(sin.getRuta() + file.getName());
        List<Path> pathsToDelete = null;
        try {
            pathsToDelete = Files.walk(rootPath).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            for (Path path : pathsToDelete) {
                Files.deleteIfExists(path);
            }
            mostrar(tilepane, textArea, stage_main);
        } catch (IOException ex) {
            Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Editar_fichero(BorderPane caja, File file, TextArea textArea, TilePane tilepane, Stage stage_main) {
        ContextMenu contextMenu = new ContextMenu();    //Crea un menu
        MenuItem Delete = new MenuItem(constantes.BORRAR);
        contextMenu.getItems().addAll(Delete);
        SingletonRutas sin = SingletonRutas.getInstancia();

        caja.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                stage_main.setTitle(file.getName());
                try {
                    sin.setCarpeta(file.getName());
                    byte[] bytes = Files.readAllBytes(Paths.get(sin.getRuta()));
                    String content = new String(bytes, StandardCharsets.UTF_8);
                    textArea.setText(content);
                } catch (IOException ex) {
                    Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                funciones.cerrar_ventana(event);
            }
        });

        caja.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(caja, e.getScreenX(), e.getScreenY());
            e.consume();
        });

        Delete.setOnAction(event -> {
            File fi = new File(sin.getRuta() + file.getName());
            fi.delete();
            try {
                mostrar(tilepane, textArea, stage_main);
            } catch (IOException ex) {
                Logger.getLogger(ventanaAcciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public static void crear_directorio(String nombre, TextArea textArea, TilePane tilepane, Stage stage_main) throws IOException {
        SingletonRutas sin = SingletonRutas.getInstancia();
        Path path = Paths.get(sin.getRuta() + nombre + "/");
        try {
            Files.createDirectory(path);
        } catch (IOException ex) {
        }

        mostrar(tilepane, textArea, stage_main); //Limpia y muestra los ficheros

    }

    public static void crear_fichero(String nombre, TextArea textArea, TilePane tilepane, Stage stage_main) throws IOException {
        SingletonRutas sin = SingletonRutas.getInstancia();
        Path path = Paths.get(sin.getRuta() + nombre + ".txt");
        try {
            Files.createFile(path);
        } catch (IOException ex) {
            System.out.println("Ya existe el archivo");
        }
        if (tilepane != null) {
            mostrar(tilepane, textArea, stage_main); //Limpia y muestra los ficheros
        } else {
            sin.setResetRuta();
            sin.setCarpeta(nombre + ".txt");
            Files.write(Paths.get(sin.getRuta()), textArea.getText().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            stage_main.setTitle(nombre + ".txt");
        }
    }

    public static void eliminar_fichero(Stage stage) {
        SingletonRutas sin = SingletonRutas.getInstancia();
        File file = new File(sin.getRuta());
        file.delete();
        stage.setTitle(constantes.TITULO_MAIN);

    }

    public static void guardar_fichero(TextArea textArea, Stage stage_main, TilePane tilepanel) throws IOException {
        SingletonRutas sin = SingletonRutas.getInstancia();
        String text = textArea.getText();
        if (stage_main.getTitle().equals(constantes.TITULO_MAIN)) {
            funciones.ventana_crear_fichero_directorio(constantes.CREAR_FICHERO, textArea, stage_main, tilepanel);
        } else {
            Files.write(Paths.get(sin.getRuta()), text.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }

    }

    public static void CreateTilePaneWIcons(TilePane titlePane, TextArea textArea, Stage stage_main, Stage stage) throws IOException {

        funciones.crear_carpeta_principal(); //Crea la carpeta si no existe
        ventanaAcciones.mostrar(titlePane, textArea, stage_main); //Muestra el panel con los ficheros

    }
}
