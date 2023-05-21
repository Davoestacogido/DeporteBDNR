package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.FoodDocumentDeserializer;
import org.ulpgc.es.model.Food;

public class MongoDBFoodDeserializer implements FoodDocumentDeserializer {
    @Override
    public Food deserialize(Document foodDocument) {
        Food food = new Food(
                foodDocument.getString("_id"),
                foodDocument.getString("alimento"),
                foodDocument.getInteger("racion"),
                (Integer) foodDocument.getOrDefault("aumento_racion", foodDocument.getInteger("racion")),
                foodDocument.getInteger("cantidad_gramos"),
                foodDocument.getInteger("calorias"),
                foodDocument.getInteger("proteinas")
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
            food.setVegan(foodDocument.getBoolean("vegano"));
    }

    private void addMealIfPresent(Document foodDocument, Food food) {
        if (foodDocument.containsKey("comida"))
            food.setMealOfTheDay(foodDocument.getList("comida", String.class));
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
}
