package org.ulpgc.es;

import org.bson.Document;
import org.ulpgc.es.model.Workout;

public interface WorkoutDocumentDeserializer {
    Workout deserialize(Document documentWorkout);
}
