/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Funciones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

    }

}
