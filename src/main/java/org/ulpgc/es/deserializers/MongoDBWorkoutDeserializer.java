package org.ulpgc.es.deserializers;

import org.bson.Document;
import org.ulpgc.es.WorkoutDocumentDeserializer;
import org.ulpgc.es.model.Workout;

public class MongoDBWorkoutDeserializer implements WorkoutDocumentDeserializer {
    /*
    Tal y como indicaba la interfaz, transforma un Documento de MongoDB a un objeto de java
     */
    @Override
    public Workout deserialize(Document workoutDocument) {
        return new Workout(
                workoutDocument.getString("_id"),
                workoutDocument.getString("tipo_ejs"),
                workoutDocument.getList("lista", String.class)
                );
    }

}

