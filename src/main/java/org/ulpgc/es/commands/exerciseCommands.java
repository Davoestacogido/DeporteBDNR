package org.ulpgc.es.commands;

import org.ulpgc.es.Command;
import org.ulpgc.es.model.Exercise;
import org.ulpgc.es.readers.MongoDBReader;

import java.util.List;
import java.util.Map;

public class exerciseCommands implements Command {
    /*
    Encargado de resolver las consultas realizadas respecto a ejercicios
     */

    private final MongoDBReader reader = new MongoDBReader();

    @Override
    public String execute(Map<String, String> parameters) {
        /*
        esta es la función principal, para las
        solicitudes sobre ejercicios, se decide su resultado en base a los parámetros que puso
        hay que entender lo siguiente
        WorkoutDay = día de entreno, que puede ser de empuje, de jalón o piernas
        Workout = entrenamiento, que puede ser del tipo hipertrofia o del tipo fuerza
         */
        if (parameters.containsKey("dia_de_entreno")) //devuelve un dia de empuje o jalon o piernas
            return buildResponse(getExercisesFromWorkoutDay(parameters));
        if (parameters.containsKey("entrenamiento")) //devuelve ejercicios de hipertrofia o fuerza
            return buildResponse(getExercisesFromWorkout(parameters));
        if (parameters.containsKey("musculo")) //devuelve ejercicios respecto a un músculo en concreto
            return buildResponse(getExercisesFromMuscle(parameters));
        return "Siga el manual para poder utilizar correctamente la app.";
    }

    private List<Exercise> getExercisesFromWorkout(Map<String, String> parameters) {
        return reader.selectExercisesFromWorkout(parameters.get("entrenamiento"), Integer.parseInt(parameters.get("num_ejs")));
    }

    private List<Exercise> getExercisesFromWorkoutDay(Map<String, String> parameters) {
        if (parameters.containsKey("musculo")) // musculo es un parámetro opcional, si lo tiene o no cambia que método del reader se llama
            return reader.selectExercisesFromWorkoutDay(
                parameters.get("dia_de_entreno"), Integer.parseInt(parameters.get("num_ejs")), parameters.get("musculo"));
        return reader.selectExercisesFromWorkoutDay(
            parameters.get("dia_de_entreno"), Integer.parseInt(parameters.get("num_ejs")));
    }

    private String buildResponse(List<Exercise> exercises) {
        StringBuilder result = new StringBuilder("La lista de ejercicios correspondientes son los siguientes: \n");
        for (Exercise exercise : exercises)
            result.append(exercise.toString()).append("\n");
        return result.toString();
    }

    private List<Exercise> getExercisesFromMuscle(Map<String, String> parameters) {
        return reader.selectExercisesFromMuscle(parameters.get("musculo"));
    }
}
