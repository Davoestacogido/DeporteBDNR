package org.ulpgc.es;

import org.ulpgc.es.model.Recipe;

public interface RecipeDeserializer {

    Recipe deserialize(String recipe);
}
