package org.ulpgc.es.model;

import java.util.List;

public class Recipe {

    private final String _id;
    private final String recipe;
    private final String preparation;
    private final List<String> foods;
    private String optional;
    private final List<Food> ingredients;

    public Recipe(String id, String recipe, String preparation, List<String> foods, List<Food> ingredients) {
        _id = id;
        this.recipe = recipe;
        this.preparation = preparation;
        this.foods = foods;
        this.ingredients = ingredients;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String get_id() {
        return _id;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getPreparation() {
        return preparation;
    }

    public List<String> getFoods() {
        return foods;
    }

    public String getOptional() {
        return optional;
    }

    public List<Food> getIngredients() {
        return ingredients;
    }
}

