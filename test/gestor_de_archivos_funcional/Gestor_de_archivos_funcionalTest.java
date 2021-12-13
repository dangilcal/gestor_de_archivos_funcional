/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional;

import gestor_de_archivos_funcional.Funciones.constantes;
import gestor_de_archivos_funcional.Funciones.funciones;
import gestor_de_archivos_funcional.Main.FXMLMainController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author dangilcal
 */
public class Gestor_de_archivos_funcionalTest extends ApplicationTest {

    @Override
    public void start(Stage st) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos_funcional.class.getResource("Main/FXMLMain.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLMainController fXMLMainControllerInstancia = fxmlLoader.getController();
        fXMLMainControllerInstancia.setStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(constantes.TITULO_MAIN);
        funciones.crear_carpeta_principal(); //Crea la carpeta si no existe
        stage.show();
        stage.setOnCloseRequest(a -> Platform.exit());
    }

    @Test
    public void testOpenAboutMenu_OK() throws InterruptedException {
        clickOn("#Menu_Info");
        clickOn("#menuInfo");
    }

    @Test
    public void testLimpiarTestos_OK() throws InterruptedException {
        clickOn("#MenuEdit");
        TextArea a = lookup("#textArea").query();
        a.setText("Hola soy dangilcal");
        clickOn("#clickNewFile");
        assertEquals(a.getText(), "");
    }

    @Test
    public void testGuardarNuevoYBorrar_OK() throws InterruptedException {
        TextArea a = lookup("#textArea").query();
        a.setText("Dangilcal ha creado el test");
        clickOn("#MenuEdit");
        clickOn("#Guardar");
        Guardar();
        clickOn("#MenuEdit");
        clickOn("#clickBorrarFile");

    }

    @Test
    public void testCrearArchivoYEditarYBorrar_OK() throws InterruptedException {
        Abrir();
        TilePane tilePane = lookup("#tilePane").query();
        Bounds bounds = tilePane.getBoundsInLocal();
        Bounds coordenada = tilePane.localToScreen(bounds);
        int x = (int) coordenada.getMinX();
        int y = (int) coordenada.getMinY();
        rightClickOn(x + 1, y + 1);
        clickOn("#crearArchivo");
        Guardar();
        BorderPane bordePane = (BorderPane) tilePane.getChildren().get(0);
        doubleClickOn(bordePane);
        clickOn("#textArea").write("Dangilcal ha creado el test");
        clickOn("#MenuEdit");
        clickOn("#Guardar");
        Abrir();
        rightClickOn(bordePane);
        clickOn("#delArchivo");
    }

    @Test
    public void testCrearCarpetaYEditarYBorrar_OK() throws InterruptedException {
        Abrir();
        TilePane tilePane = lookup("#tilePane").query();
        Bounds bounds = tilePane.getBoundsInLocal();
        Bounds coordenada = tilePane.localToScreen(bounds);
        int x = (int) coordenada.getMinX();
        int y = (int) coordenada.getMinY();
        rightClickOn(x + 1, y + 1);
        clickOn("#crearCarpeta");
        Guardar();
        BorderPane bordePane = (BorderPane) tilePane.getChildren().get(0);
        doubleClickOn(bordePane);

        rightClickOn(x + 1, y + 1);
        clickOn(x + 100, y + 100);
        rightClickOn(x + 1, y + 1);
        clickOn("#crearCarpeta");
        Guardar();
        bordePane = (BorderPane) tilePane.getChildren().get(0);
        doubleClickOn(bordePane);
        clickOn("#retroceder");
        clickOn("#retroceder");
        bordePane = (BorderPane) tilePane.getChildren().get(0);
        rightClickOn(bordePane);
        clickOn("#delCarpeta");
        clickOn("#retroceder");
    }

    @Test
    public void testCancelarCreacionArchivoOCarperta_OK() {
        clickOn("#MenuEdit");
        clickOn("#Guardar");
        clickOn("#create");
        clickOn("#cancelarCreacion");

    }

    public void Abrir() {
        clickOn("#MenuEdit");
        clickOn("#clickAbrir");
    }

    public void Guardar() {
        clickOn("#tField").write("1Test");
        clickOn("#create");
    }

}
