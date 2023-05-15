package org.ulpgc.es.model;

public class Ejercicio {

    private final String _id;
    private final String ejercicio;
    private final int[] reps;
    private final String musculo;
    private final String tipo;

    public Ejercicio(String id, String ejercicio, int[] reps, String musculo, String tipo) {
        _id = id;
        this.ejercicio = ejercicio;
        this.reps = reps;
        this.musculo = musculo;
        this.tipo = tipo;
    }
}
