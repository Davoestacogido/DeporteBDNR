package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.RecipeDocumentDeserializer;
import org.ulpgc.es.model.Recipe;

public class MongoDBRecipeDeserializer implements RecipeDocumentDeserializer {
    @Override
    public Recipe deserialize(Document recipe) {
        return null;
    }
}
