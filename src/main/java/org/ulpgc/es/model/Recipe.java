package org.ulpgc.es.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private final String _id;
    private final String recipe;
    private final String preparation;
    private final List<String> meals;
    private String optional;
    private final List<String> ingredientsIds;
    private List<Food> ingredients = new ArrayList<>();

    public Recipe(String id, String recipe, String preparation, List<String> meals, List<String> ingredientsIds) {
        this._id = id;
        this.recipe = recipe;
        this.preparation = preparation;
        this.meals = meals;
        this.ingredientsIds = ingredientsIds;
    }

    public boolean add(Food food) {
        return getIngredients().add(food);
    }

    public List<Food> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Food> ingredients) {
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

    public List<String> getMeals() {
        return meals;
    }

    public String getOptional() {
        return optional;
    }

    public List<String> getIngredientsIds() {
        return ingredientsIds;
    }
}

