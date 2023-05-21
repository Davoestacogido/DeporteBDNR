package org.ulpgc.es.model;

import java.util.List;

public class Exercise {

    private final String _id;
    private final String exercise;
    private final List<Integer> reps;
    private final String muscle;
    private final String type;

    public Exercise(String id, String exercise, List<Integer> reps, String muscle, String type) {
        this._id = id;
        this.exercise = exercise;
        this.reps = reps;
        this.muscle = muscle;
        this.type = type;
    }

    public String get_id() {
        return _id;
    }

    public String getExercise() {
        return exercise;
    }

    public List<Integer> getReps() {
        return reps;
    }

    public String getMuscle() {
        return muscle;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "El ejercicio enfocado en el trabajo de \"" + type + "\" es \"" + exercise + "\" trabaja de forma directa \"" + muscle + "\"" + "\n" +
            "El numero de repeticiones a hacer es \"" + reps + "\"";
    }
}
