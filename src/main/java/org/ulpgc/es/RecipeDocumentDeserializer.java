package org.ulpgc.es;

import org.bson.Document;
import org.ulpgc.es.model.Recipe;

public interface RecipeDocumentDeserializer {
    /*
    Mismo caso que en ExerciseDocumentDeserializer pero para recetas
     */
    Recipe deserialize(Document documentRecipe);
}
