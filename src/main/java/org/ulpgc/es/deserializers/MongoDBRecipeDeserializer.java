package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.RecipeDocumentDeserializer;
import org.ulpgc.es.model.Recipe;

public class MongoDBRecipeDeserializer implements RecipeDocumentDeserializer {
    /*
    Tal y como indicaba la interfaz, transforma un Documento de MongoDB a un objeto de java
     */

    @Override
    public Recipe deserialize(Document recipeDocument) {
        Recipe recipe = new Recipe(
                recipeDocument.getString("_id"),
                recipeDocument.getString("receta"),
                recipeDocument.getString("preparacion"),
                recipeDocument.getList("comida", String.class),
                recipeDocument.getList("ingredientes", String.class)
        );
        addOptionalFieldIfPresent(recipeDocument, recipe);
        return recipe;
    }

    private void addOptionalFieldIfPresent(Document recipeDocument, Recipe recipe) {
        if (recipeDocument.containsKey("opcional"))
            recipe.setOptional(recipeDocument.getString(recipeDocument.getString("opcional")));
    }
}
