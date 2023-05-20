package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.WorkoutDocumentDeserializer;
import org.ulpgc.es.model.Workout;

import java.util.List;

public class MongoDBWorkoutDeserializer implements WorkoutDocumentDeserializer {
    @Override
    public Workout deserialize(Document workoutDocument) {
        return new Workout(
                workoutDocument.getString("tipo_ejs"),
                toList(workoutDocument.getString("lista"))
                );
    }

    private List<String> toList(String ejsList) {
        return List.of(ejsList.substring(0, ejsList.length() - 1).split(","));
    }
}

