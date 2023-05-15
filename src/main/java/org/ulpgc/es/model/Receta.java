package org.ulpgc.es.model;

import java.util.List;

public class Receta {

    private final String _id;
    private final String receta;
    private final String preparacion;
    private final List<String> comida;
    private String opcional;
    private final List<Alimento> ingredientes;

    public Receta(String id, String receta, String preparacion, List<String> comida, List<Alimento> ingredientes) {
        _id = id;
        this.receta = receta;
        this.preparacion = preparacion;
        this.comida = comida;
        this.ingredientes = ingredientes;
    }

    public void setOpcional(String opcional) {
        this.opcional = opcional;
    }

    public String get_id() {
        return _id;
    }

    public String getReceta() {
        return receta;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public List<String> getComida() {
        return comida;
    }

    public String getOpcional() {
        return opcional;
    }

    public List<Alimento> getIngredientes() {
        return ingredientes;
    }
}

