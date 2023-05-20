package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.ExerciseDocumentDeserializer;
import org.ulpgc.es.model.Exercise;

public class MongoDBExerciseDeserializer implements ExerciseDocumentDeserializer {
    @Override
    public Exercise deserialize(Document exerciseDocument) {
        return new Exercise(
                exerciseDocument.getString("_id"),
                exerciseDocument.getString("ejercicio"),
                exerciseDocument.getList("reps",Integer.class),
                exerciseDocument.getString("musculo"),
                exerciseDocument.getString("tipo")
        );
    }
}
