/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Funciones;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dangilcal
 */
public class funcionesTest {

    public funcionesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of crear_carpeta_principal method, of class funciones.
     */
    @Test
    public void testCarpetaRaiz_ok() {
        System.out.println("crear_carpeta_principal");
        funciones.crear_carpeta_principal();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of ventana_mostrar_info method, of class funciones.
     */
//    @Test
//    public void testVentanaInformacion_ko() {
//        System.out.println("ventana_mostrar_info");
//        funciones.ventana_mostrar_info();
//        // TODO review the generated test code and remove the default call to fail.
//    }
    /**
     * Test of ventana_mostrar_ficheros method, of class funciones.
     */
//    @Test
//    public void testVentanaAbrir_ko() {
//        System.out.println("ventana_mostrar_ficheros");
//        TextArea textArea = null;
//        Stage stage_main = null;
//        funciones.ventana_mostrar_ficheros(textArea, stage_main);
//        // TODO review the generated test code and remove the default call to fail.
//    }
    /**
     * Test of ventana_crear_fichero_directorio method, of class funciones.
     */
//    @Test
//    public void testVentanaDeCracionVariable_ko() throws Exception {
//        System.out.println("ventana_crear_fichero_directorio");
//        String nombreLabel = "";
//        TextArea textArea = null;
//        Stage stage_main = null;
//        TilePane tilepanel = null;
//        funciones.ventana_crear_fichero_directorio(nombreLabel, textArea, stage_main, tilepanel);
//        // TODO review the generated test code and remove the default call to fail.
//    }
    /**
     * Test of cerrar_ventana method, of class funciones.
     */
    @Test
    public void testCerrarVentanaActual_ok() {
        System.out.println("cerrar_ventana");
        MouseEvent event = null;
        funciones.cerrar_ventana(event);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of titulo_ventana method, of class funciones.
     */
    @Test
    public void testTituloVentanaActual_ok() {
        System.out.println("titulo_ventana");
        MouseEvent event = null;
        String titulo = "";
        funciones.titulo_ventana(event, titulo);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of stage_abrir method, of class funciones.
     */
    @Test
    public void testRecojerStage_ok() {
        System.out.println("stage_abrir");
        MouseEvent event = null;
        Stage expResult = null;
        Stage result = funciones.stage_abrir(event);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
