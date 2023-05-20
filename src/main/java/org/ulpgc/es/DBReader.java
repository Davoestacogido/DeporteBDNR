package org.ulpgc.es;

import org.ulpgc.es.model.Exercise;
import org.ulpgc.es.model.Food;

import java.util.List;

public interface DBReader {

    List<Food> selectAllFood();
    List<Exercise> selectAllExercises();
}