package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.WorkoutDocumentDeserializer;
import org.ulpgc.es.model.Workout;

public class MongoDBWorkoutDeserializer implements WorkoutDocumentDeserializer {
    @Override
    public Workout deserialize(Document workout) {
        return null;
    }
}
