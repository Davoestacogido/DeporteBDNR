package org.ulpgc.es.model;

import java.util.List;

public class Workout {

    private final String typeExercises;
    private final List<String> exercisesIds;

    public Workout(String typeExercises, List<String> exercisesIds) {
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
