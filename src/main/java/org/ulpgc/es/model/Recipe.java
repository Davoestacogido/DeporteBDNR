package org.ulpgc.es.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return "La receta de \"" + recipe + "\", su preparacion consiste en lo siguiente: " + preparation + "\n" +
            (optional!= null ? "Adicional: " + optional + "\n": "") +
            getFoodMeaningfulInformation();
    }

    private String getFoodMeaningfulInformation() {
        StringBuilder result = new StringBuilder("");
        for (Food ingredient : ingredients)
            result.append("Del ingrediente \"").append(ingredient.getFood()).append("\" debemos usar ")
                .append(ingredient.getGramsAmount()).append("\n");
        return result.toString();
    }
}

