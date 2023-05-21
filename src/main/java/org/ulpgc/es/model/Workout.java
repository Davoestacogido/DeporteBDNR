package org.ulpgc.es.model;

import java.util.List;

public class Workout {

    /*
    Es el equivalente a los JSON de entrenamiento
     */
    private final String _id;
    private final String typeExercises;
    private final List<String> exercisesIds;

    public Workout(String id, String typeExercises, List<String> exercisesIds) {
        this._id = id;
        this.typeExercises = typeExercises;
        this.exercisesIds = exercisesIds;
    }

    public String getTypeExercises() {
        return typeExercises;
    }

    public List<String> getExercisesIds() {
        return exercisesIds;
    }
}
