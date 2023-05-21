package org.ulpgc.es;

import org.bson.Document;
import org.ulpgc.es.model.Workout;

public interface WorkoutDocumentDeserializer {
    /*
Mismo caso que en ExerciseDocumentDeserializer pero para entrenamientos
 */
    Workout deserialize(Document documentWorkout);
}
