package org.ulpgc.es;

import org.bson.Document;
import org.ulpgc.es.model.Recipe;

public interface RecipeDocumentDeserializer {

    Recipe deserialize(Document documentRecipe);
}
