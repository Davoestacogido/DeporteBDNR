package org.ulpgc.es.model;

import java.util.List;

public class Entrenamiento {

    private final String tipo_ejs;
    private final List<Ejercicio> lista;

    public Entrenamiento(String tipo_ejs, List<Ejercicio> lista) {
        this.tipo_ejs = tipo_ejs;
        this.lista = lista;
    }

    public String getTipo_ejs() {
        return tipo_ejs;
    }

    public List<Ejercicio> getLista() {
        return lista;
    }
}
