package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.FoodDocumentDeserializer;
import org.ulpgc.es.model.Food;

import java.util.Arrays;

public class MongoDBFoodDeserializer implements FoodDocumentDeserializer {
    @Override
    public Food deserialize(Document foodDocument) {
        Food food = new Food(
                foodDocument.getString("_id"),
                foodDocument.getString("alimento"),
                (Integer) foodDocument.get("racion"),
                (Integer) foodDocument.get("cantidad_gramos"),
                (Integer) foodDocument.get("calorias"),
                (Integer) foodDocument.get("proteinas")
                );
        addOptionalFieldsIfPresent(foodDocument, food);
        return food;

    }

    private void addOptionalFieldsIfPresent(Document foodDocument, Food food) {
        addAccompanimentIfPresent(foodDocument, food);
        addProvideIfPresent(foodDocument, food);
        addOriginIfPresent(foodDocument, food);
        addSpicesIfPresent(foodDocument, food);
        addMealIfPresent(foodDocument, food);
        addVeganIfPresent(foodDocument, food);
        addAnotationIfPresent(foodDocument, food);
    }

    private void addAnotationIfPresent(Document foodDocument, Food food) {
        if (foodDocument.containsKey("anotacion"))
            food.setAnotation(foodDocument.getString("anotacion"));
    }

    private void addVeganIfPresent(Document foodDocument, Food food) {
        if (foodDocument.containsKey("vegano"))
            food.setVegan(Boolean.parseBoolean(foodDocument.getString("vegano")));
    }

    private void addMealIfPresent(Document foodDocument, Food food) {
        if (foodDocument.containsKey("comida"))
            food.setMealOfTheDay(Arrays.toString(to_list(foodDocument.getString("comida"))));
    }

    private void addSpicesIfPresent(Document foodDocument, Food food) {
        if (foodDocument.containsKey("especias"))
            food.setSpices(foodDocument.getString("especias"));
    }

    private void addOriginIfPresent(Document foodDocument, Food food) {
        if (foodDocument.containsKey("origen"))
            food.setOrigin(foodDocument.getString("origen"));
    }

    private void addProvideIfPresent(Document foodDocument, Food food) {
        if (foodDocument.containsKey("aportan"))
            food.setProvide(foodDocument.getString("aportan"));
    }

    private void addAccompanimentIfPresent(Document foodDocument, Food food) {
        if (foodDocument.containsKey("acompanamiento"))
            food.setAccompaniment(foodDocument.getString("acompanamiento"));
    }

    private String[] to_list(String foodDocument) {
        return foodDocument.substring(0, foodDocument.length() - 1).split(",");
    }
}
