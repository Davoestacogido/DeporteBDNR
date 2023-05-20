package org.ulpgc.es;

import org.ulpgc.es.model.Exercise;

public interface ExerciseDeserializer {
    Exercise deserialize(String exercise);
}
