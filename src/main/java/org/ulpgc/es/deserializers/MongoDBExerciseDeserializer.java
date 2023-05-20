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
                toIntArray(exerciseDocument.getString("reps")),
                exerciseDocument.getString("muscle"),
                exerciseDocument.getString("type")
        );
    }

    private int[] toIntArray(String reps) {
        return getReps(reps.substring(0, reps.length() - 1).split(","));
    }

    private int[] getReps(String[] reps) {
        int[] intArray = new int[reps.length];
        for (int i = 0; i < reps.length; i++)
            intArray[i] = Integer.parseInt(reps[i].trim());
        return intArray;
    }
}
