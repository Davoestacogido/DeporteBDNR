package org.ulpgc.es.readers;

import com.mongodb.client.*;
import org.ulpgc.es.DBReader;
import org.bson.Document;
import org.ulpgc.es.deserializers.MongoDBExerciseDeserializer;
import org.ulpgc.es.deserializers.MongoDBFoodDeserializer;
import org.ulpgc.es.deserializers.MongoDBRecipeDeserializer;
import org.ulpgc.es.deserializers.MongoDBWorkoutDeserializer;
import org.ulpgc.es.model.Exercise;
import org.ulpgc.es.model.Food;
import org.ulpgc.es.model.Recipe;
import org.ulpgc.es.model.Workout;

import java.util.*;

import static com.mongodb.client.model.Aggregates.limit;

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
    Random random = new Random();

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

    public List<Recipe> selectOneDayDiet(boolean vegan) {
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(getMeal("desayuno", vegan));
        recipeList.add(getMeal("almuerzo", vegan));
        recipeList.add(getMeal("cena", vegan));
        return recipeList;
    }

    private Recipe getMeal(String desayuno, boolean vegan) {
        Document recipeQuery = new Document("comida", desayuno);
        return getRecipe(vegan, recipeQuery);
    }

    private Recipe getRecipe(boolean vegan, Document recipeQuery) {
        int randomIndex = random.nextInt((int) recipes.countDocuments(recipeQuery));
        Document document = recipes.find(recipeQuery).limit(1).skip(randomIndex).first();
        Recipe recipe = recipeDeserializer.deserialize(document);
        addRecipeIngredients(recipe);
        if (vegan && recipe.getIngredients().stream().anyMatch(food -> food.isVegan() != vegan))
            return getNonRandomRecipe(recipeQuery, vegan);
        return recipe;
    }

    private Recipe getNonRandomRecipe(Document recipeQuery, boolean vegan) {
        for (Document document : recipes.find(recipeQuery)) {
            Recipe recipe = recipeDeserializer.deserialize(document);
            addRecipeIngredients(recipe);
            if (vegan && recipe.getIngredients().stream().allMatch(food -> food.isVegan() == vegan))
                return recipe;
        }
        return null;
    }

    private void addRecipeIngredients(Recipe recipe) {
        for (String ingredientId : recipe.getIngredientsIds())
            recipe.add(foodDeserializer.deserialize(Objects.requireNonNull(foods.find(new Document("_id", ingredientId)).first())));
    }
}
