package org.ulpgc.es.model;

import java.util.List;

public class Workout {

    private final String typeExercises;
    private final List<Exercise> list;

    public Workout(String typeExercises, List<Exercise> list) {
        this.typeExercises = typeExercises;
        this.list = list;
    }

    public String getTypeExercises() {
        return typeExercises;
    }

    public List<Exercise> getList() {
        return list;
    }
}
