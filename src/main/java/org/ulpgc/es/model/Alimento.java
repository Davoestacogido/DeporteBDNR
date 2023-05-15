package org.ulpgc.es.model;

public class Alimento {

    private final String _id;
    private final String alimento;
    private final int racion;
    private int cantidadGramos;
    private final int caloriasPer100g;
    private final int proteinasPer100g;
    private boolean vegano = false;
    private String origen;
    private String aportan;
    private String especias;
    private String tipo;
    private String acompanamiento;
    private String anotacion;
    private String comida;

    public Alimento(String id, String alimento, int racion, int cantidadGramos, int caloriasPer100g, int proteinasPer100g) {
        _id = id;
        this.alimento = alimento;
        this.racion = racion;
        this.cantidadGramos = cantidadGramos;
        this.caloriasPer100g = caloriasPer100g;
        this.proteinasPer100g = proteinasPer100g;
    }

    public Alimento increaseRacion() {
        this.cantidadGramos = this.cantidadGramos + this.racion;
        return this;
    }

    public int getCaloriasPer100g() {
        return Math.round(caloriasPer100g * (this.cantidadGramos/100));
    }

    public int getProteinasPer100g() {
        return Math.round(proteinasPer100g * (this.cantidadGramos/100));
    }

    public void setVegano(boolean vegano) {
        this.vegano = vegano;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setAportan(String aportan) {
        this.aportan = aportan;
    }

    public void setEspecias(String especias) {
        this.especias = especias;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAcompanamiento(String acompanamiento) {
        this.acompanamiento = acompanamiento;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String get_id() {
        return _id;
    }

    public String getAlimento() {
        return alimento;
    }

    public int getRacion() {
        return racion;
    }

    public int getCantidadGramos() {
        return cantidadGramos;
    }

    public boolean isVegano() {
        return vegano;
    }

    public String getOrigen() {
        return origen;
    }

    public String getAportan() {
        return aportan;
    }

    public String getEspecias() {
        return especias;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAcompanamiento() {
        return acompanamiento;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public String getComida() {
        return comida;
    }
}