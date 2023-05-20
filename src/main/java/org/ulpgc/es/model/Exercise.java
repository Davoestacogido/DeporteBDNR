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
}
