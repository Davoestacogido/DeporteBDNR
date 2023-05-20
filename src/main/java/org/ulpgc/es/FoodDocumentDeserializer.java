package org.ulpgc.es;

import org.bson.Document;
import org.ulpgc.es.model.Food;

public interface FoodDocumentDeserializer {
    Food deserialize(Document food);

}
