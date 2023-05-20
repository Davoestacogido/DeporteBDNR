package org.ulpgc.es;

import org.ulpgc.es.model.Food;

public interface FoodDocumentDeserializer {
    Food deserialize(String food);

}
