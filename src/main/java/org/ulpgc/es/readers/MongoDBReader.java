package org.ulpgc.es.readers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.ulpgc.es.DBReader;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.ulpgc.es.deserializers.MongoDBExerciseDeserializer;
import org.ulpgc.es.deserializers.MongoDBFoodDeserializer;
import org.ulpgc.es.deserializers.MongoDBRecipeDeserializer;
import org.ulpgc.es.deserializers.MongoDBWorkoutDeserializer;
import org.ulpgc.es.model.Exercise;
import org.ulpgc.es.model.Food;
import org.ulpgc.es.model.Recipe;
import org.ulpgc.es.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class MongoDBReader implements DBReader {

    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = mongoClient.getDatabase("deporte");
    MongoCollection<Document> foods = database.getCollection("comidas");
    MongoCollection<Document> exercises = database.getCollection("ejercicios");
    MongoCollection<Document> workouts = database.getCollection("entrenamientos");
    MongoCollection<Document> recipes = database.getCollection("recetas");
    MongoDBExerciseDeserializer exerciseDeserializer = new MongoDBExerciseDeserializer();
    MongoDBFoodDeserializer foodDeserializer = new MongoDBFoodDeserializer();
    MongoDBRecipeDeserializer recipeDeserializer = new MongoDBRecipeDeserializer();
    MongoDBWorkoutDeserializer workoutDeserializer = new MongoDBWorkoutDeserializer();

    public List<Food> selectAllFood() {
        List<Food> foodList = new ArrayList<>();
        for (Document document : foods.find())
            foodList.add(foodDeserializer.deserialize(document));
        return foodList;
    }

    public List<Exercise> selectAllExercises() {
        List<Exercise> exercisesList = new ArrayList<>();
        for (Document document : exercises.find())
            exercisesList.add(exerciseDeserializer.deserialize(document));
        return exercisesList;
    }

    public List<Workout> selectAllWorkouts() {
        List<Workout> workoutList = new ArrayList<>();
        for (Document document : workouts.find())
            workoutList.add(workoutDeserializer.deserialize(document));
        return workoutList;
    }

    public List<Recipe> selectAllRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        for (Document document : recipes.find())
            recipeList.add(recipeDeserializer.deserialize(document));
        return recipeList;
    }
}
