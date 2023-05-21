package org.ulpgc.es;

import org.ulpgc.es.model.Exercise;
import org.ulpgc.es.model.Food;

import java.util.List;

public interface DBReader {
    /*
    Interfaz para implementaciones de lector de base de datos,
     las interfaces son muy útiles para crear un código más flexible y escalable
     */
    List<Food> selectAllFood();
    List<Exercise> selectAllExercises();
}