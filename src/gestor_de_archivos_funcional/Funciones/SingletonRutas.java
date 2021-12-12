/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos_funcional.Funciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangilcal
 */
public class SingletonRutas {

    private static SingletonRutas rutas;
    private List lista_ruta;

    public static SingletonRutas getInstancia() {
        if (rutas == null) {
            rutas = new SingletonRutas();
        }
        return rutas;
    }

    private SingletonRutas() {
        this.lista_ruta = new ArrayList();
        this.lista_ruta.add(constantes.CARPETA_RAIZ);
    }

    public String getRuta() {
        String ruta = "";
        String temporal = "";
        for (int i = 0; i < lista_ruta.size(); i++) {
            ruta = lista_ruta.get(i) + "/";
            temporal += ruta;
        }
        return temporal;
    }

    public void setCarpeta(String a) {
        lista_ruta.add(a);
    }

    public void setResetRuta() {
        lista_ruta.clear();
        this.lista_ruta.add(constantes.CARPETA_RAIZ);
    }

    public void setRutaAnterior() {
        if (lista_ruta.size() > 1) {
            lista_ruta.remove(lista_ruta.size() - 1);
        }
    }

}
