package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.FoodDocumentDeserializer;
import org.ulpgc.es.model.Food;
public class MongoDBFoodDeserializer implements FoodDocumentDeserializer {
    @Override
    public Food deserialize(Document foodDocument) {
        Food food = new Food(
                foodDocument.get("_id").toString(),
                foodDocument.get("alimento").toString(),
                (Integer) foodDocument.get("racion"),
                (Integer) foodDocument.get("cantidad_gramos"),
                (Integer) foodDocument.get("calorias"),
                (Integer) foodDocument.get("proteinas")
                );
        return food;

    }
}
