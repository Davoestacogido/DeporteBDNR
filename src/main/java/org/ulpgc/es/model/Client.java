package org.ulpgc.es.model;

public class Client {
    private final int weight;
    private final int height;
    private final String diet;
    private final boolean vegan;
    private final String activity;
    private final int age;
    private int proteinRecommended;
    private int calorieRecommended;
    private final String gender;


    public Client(int weight, int height, String diet, boolean vegan, String activity, int age, String gender) {
        this.weight = weight;
        this.height = height;
        this.diet = diet;
        this.vegan = vegan;
        this.activity = activity;
        this.age = age;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public int getProteinRecommended() {
        return proteinRecommended;
    }

    public int getCalorieRecommended() {
        return calorieRecommended;
    }

    public void setProteinRecommended(int proteinRecommended) {
        this.proteinRecommended = proteinRecommended;
    }

    public void setCalorieRecommended(int calorieRecommended) {
        this.calorieRecommended = calorieRecommended;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getDiet() {
        return diet;
    }

    public boolean isVegan() {
        return vegan;
    }

    public String getActivity() {
        return activity;
    }

    public int getAge() {
        return age;
    }
}
