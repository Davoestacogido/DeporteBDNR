package org.ulpgc.es.model;

public class Diet {
    /*
       Indica información acerca de la dieta que se le recomendará a un cliente
 */
    private final Recipe breakfast;
    private final Recipe lunch;
    private final Recipe dinner;
    private double calorieRecommended;
    private double proteinRecommended;
    private double realCalories;
    private double realProteins;
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

    public void setCalorieRecommended(double calorieRecommended) {
        this.calorieRecommended = calorieRecommended;
    }

    public void setProteinRecommended(double proteinRecommended) {
        this.proteinRecommended = proteinRecommended;
    }

    public void setRealCalories(double realCalories) {
        this.realCalories = realCalories;
    }

    public void setRealProteins(double realProteins) {
        this.realProteins = realProteins;
    }

    public Recipe getLunch() {
        return lunch;
    }

    public Recipe getDinner() {
        return dinner;
    }

    public double getCalorieRecommended() {
        return calorieRecommended;
    }

    public double getProteinRecommended() {
        return proteinRecommended;
    }

    public double getRealCalories() {
        return realCalories;
    }

    public double getRealProteins() {
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
