package org.ulpgc.es.model;

import java.util.List;

public class Food {

    private final String _id;
    private final String food;
    private int ration;
    private final int rationIncrease;
    private final int gramsAmount;
    private final int caloriesPer100g;
    private final int proteinsPer100g;
    private boolean vegan = false;
    private String origin;
    private String provide;
    private String spices;
    private String type;
    private String accompaniment;
    private String anotation;
    private List<String> mealOfTheDay;

    public Food(String id, String food, int ration, int rationIncrease, int gramsAmount, int caloriesPer100g, int proteinsPer100g) {
        this._id = id;
        this.food = food;
        this.ration = ration;
        this.rationIncrease = rationIncrease;
        this.gramsAmount = gramsAmount;
        this.caloriesPer100g = caloriesPer100g;
        this.proteinsPer100g = proteinsPer100g;
    }

    public void increaseRacion() {
        this.ration = this.ration + this.rationIncrease;
    }

    public void decreaseRacion() {
        this.ration = Math.max(0, this.ration - this.rationIncrease);
    }

    public int getCaloriesPer100g() {
        return Math.round(caloriesPer100g * (this.gramsAmount /100));
    }

    public int getProteinsPer100g() {
        return Math.round(proteinsPer100g * (this.gramsAmount /100));
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setProvide(String provide) {
        this.provide = provide;
    }

    public void setSpices(String spices) {
        this.spices = spices;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccompaniment(String accompaniment) {
        this.accompaniment = accompaniment;
    }

    public void setAnotation(String anotation) {
        this.anotation = anotation;
    }

    public void setMealOfTheDay(List<String> mealOfTheDay) {
        this.mealOfTheDay = mealOfTheDay;
    }

    public String get_id() {
        return _id;
    }

    public String getFood() {
        return food;
    }

    public int getRation() {
        return ration;
    }

    public int getGramsAmount() {
        return gramsAmount;
    }

    public boolean isVegan() {
        return vegan;
    }

    public String getOrigin() {
        return origin;
    }

    public String getProvide() {
        return provide;
    }

    public String getSpices() {
        return spices;
    }

    public String getType() {
        return type;
    }

    public String getAccompaniment() {
        return accompaniment;
    }

    public String getAnotation() {
        return anotation;
    }

    public List<String> getMealOfTheDay() {
        return mealOfTheDay;
    }

    @Override
    public String toString() {
        return "La siguiente comida \"" + food + "\" conta de las siguientes caracteristicas: \n" +
            "Calorias cada 100 gramos \"" + caloriesPer100g + "\". " +
            "Proteinas cada 100 gramos \"" + proteinsPer100g + "\". " +
            (origin!= null ? "Es de origen: \"" + origin + "\"\n": "") +
            (provide!= null ? "La comida aporta: \"" + provide + "\"\n": "") +
            (spices!= null ? "Recomendaciones para cocinar: \"" + spices + "\"\n": "") +
            (type!= null ? "Es de tipo: \"" + type + "\"\n": "") +
            (accompaniment!= null ? "se recomienda acompañar con: \"" + accompaniment + "\"\n": "") +
            (anotation!= null ? "Puntos a tener en cuenta: \"" + anotation + "\"\n": "") +
            "Vegana \"" + vegan + "\" " +
            "La cantidad de comida (racion) que debera consumir del alimento es: \"" + ration + "\"";
    }
}