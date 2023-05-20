package org.ulpgc.es;

import org.ulpgc.es.model.Workout;
import org.ulpgc.es.readers.MongoDBReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Workout> workouts = new MongoDBReader().selectAllWorkouts();
        System.out.println();
    }
}
