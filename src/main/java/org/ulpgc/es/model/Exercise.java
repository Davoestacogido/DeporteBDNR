package org.ulpgc.es.model;

public class Exercise {

    private final String _id;
    private final String exercise;
    private final int[] reps;
    private final String muscle;
    private final String type;

    public Exercise(String id, String exercise, int[] reps, String muscle, String type) {
        _id = id;
        this.exercise = exercise;
        this.reps = reps;
        this.muscle = muscle;
        this.type = type;
    }
}
