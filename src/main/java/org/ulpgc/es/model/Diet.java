package org.ulpgc.es.model;

public class Diet {
    private final Recipe breakfast;
    private final Recipe lunch;
    private final Recipe dinner;
    private int calorieRecommended;
    private int proteinRecommended;
    private int realCalories;
    private int realProteins;
    private Client client;

    public Diet(Recipe breakfast, Recipe lunch, Recipe dinner) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Client getClient() {
        return client;
    }

    public Recipe getBreakfast() {
        return breakfast;
    }

    public void setCalorieRecommended(int calorieRecommended) {
        this.calorieRecommended = calorieRecommended;
    }

    public void setProteinRecommended(int proteinRecommended) {
        this.proteinRecommended = proteinRecommended;
    }

    public void setRealCalories(int realCalories) {
        this.realCalories = realCalories;
    }

    public void setRealProteins(int realProteins) {
        this.realProteins = realProteins;
    }

    public Recipe getLunch() {
        return lunch;
    }

    public Recipe getDinner() {
        return dinner;
    }

    public int getCalorieRecommended() {
        return calorieRecommended;
    }

    public int getProteinRecommended() {
        return proteinRecommended;
    }

    public int getRealCalories() {
        return realCalories;
    }

    public int getRealProteins() {
        return realProteins;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "La dieta es del tipo: " + client.getDiet() + "\n" +
            "Desayuno: \n" + breakfast.toString() + "\n" +
            "Almuerzo: \n" + lunch.toString() + "\n" +
            "Cena: \n" + dinner.toString() + "\n" +
            "Calorias recomendadas: " + calorieRecommended + "\n" +
            "Proteinas recomendadas: " + proteinRecommended + "\n" +
            "Calorias reales del dia propuesto: " + realCalories + "\n" +
            "Proteinas reales del dia propuesto: " + realProteins + "\n";
    }
}
