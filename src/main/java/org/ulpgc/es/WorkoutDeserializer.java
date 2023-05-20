package org.ulpgc.es;

import org.ulpgc.es.model.Workout;

public interface WorkoutDeserializer {
    Workout deserialize(String workout);
}
