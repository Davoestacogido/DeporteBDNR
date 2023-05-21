package org.ulpgc.es;

import org.bson.Document;
import org.ulpgc.es.model.Food;

public interface FoodDocumentDeserializer {
    /*
    Mismo caso que en ExerciseDocumentDeserializer pero para Alimentos
     */
    Food deserialize(Document foodDocument);

}
