package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.ExerciseDocumentDeserializer;
import org.ulpgc.es.model.Exercise;

public class MongoDBExerciseDeserializer implements ExerciseDocumentDeserializer {
    @Override
    public Exercise deserialize(Document exercise) {
        return null;
    }
}
