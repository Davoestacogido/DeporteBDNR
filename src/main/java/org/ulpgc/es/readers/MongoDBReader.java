package org.ulpgc.es.readers;

import com.mongodb.client.*;
import org.bson.Document;
import org.ulpgc.es.DBReader;
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
import java.util.Objects;
import java.util.Random;

public class MongoDBReader implements DBReader {
    /*
    Esta es una de las clases más importantes, se encarga de establecer una conexión a la base de datos y a sus colecciones
    una vez las establece, esta preparada para realizar todo tipo de consultas.
     */

    private final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private final MongoDatabase database = mongoClient.getDatabase("deporte");
    private final MongoCollection<Document> foods = database.getCollection("comidas");
    private final MongoCollection<Document> exercises = database.getCollection("ejercicios");
    private final MongoCollection<Document> workouts = database.getCollection("entrenamientos");
    private final MongoCollection<Document> recipes = database.getCollection("recetas");
    private final MongoDBExerciseDeserializer exerciseDeserializer = new MongoDBExerciseDeserializer();
    private final MongoDBFoodDeserializer foodDeserializer = new MongoDBFoodDeserializer(); // hace uso de los
    // deserializadores para pasar de Documento de MongoDB a objeto de Java
    private final MongoDBRecipeDeserializer recipeDeserializer = new MongoDBRecipeDeserializer();
    private final MongoDBWorkoutDeserializer workoutDeserializer = new MongoDBWorkoutDeserializer();
    private final Random random = new Random();

    public List<Food> selectAllFood() {// lee todos los documentos de comida
        List<Food> foodList = new ArrayList<>();
        for (Document document : foods.find())
            foodList.add(foodDeserializer.deserialize(document));
        return foodList;
    }

    public List<Exercise> selectAllExercises() {// lee todos los documentos de ejercicios
        List<Exercise> exercisesList = new ArrayList<>();
        for (Document document : exercises.find())
            exercisesList.add(exerciseDeserializer.deserialize(document));
        return exercisesList;
    }

    public List<Workout> selectAllWorkouts() {// lee todos los documentos de entrenamientos
        List<Workout> workoutList = new ArrayList<>();
        for (Document document : workouts.find())
            workoutList.add(workoutDeserializer.deserialize(document));
        return workoutList;
    }

    public List<Recipe> selectAllRecipes() {// lee todos los documentos de recetas
        List<Recipe> recipeList = new ArrayList<>();
        for (Document document : recipes.find())
            recipeList.add(recipeDeserializer.deserialize(document));
        return recipeList;
    }

    public List<Recipe> selectOneDayDiet(boolean vegan) {// trata de obtener de forma aleatoria un día de dieta
        List<Recipe> recipeList = new ArrayList<>();     // (desayuno almuerzo y cena) si es vegana, se ajusta como tal
        recipeList.add(getMeal("desayuno", vegan));
        recipeList.add(getMeal("almuerzo", vegan));
        recipeList.add(getMeal("cena", vegan, recipeList.get(1).get_id()));
        return recipeList;
    }

    private Recipe getMeal(String meal, boolean vegan) {
        Document recipeQuery = new Document("comida", meal);
        return getRecipe(vegan, recipeQuery);
    }

    private Recipe getMeal(String meal, boolean vegan, String usedFoodId) {
        Document recipeQuery = new Document("comida", meal);
        recipeQuery.append("_id", new Document("$ne",usedFoodId));
        return getRecipe(vegan, recipeQuery);
    }

    private Recipe getRecipe(boolean vegan, Document recipeQuery) { //para cada comida obtiene una receta de que se comerá
        int randomIndex = random.nextInt((int) recipes.countDocuments(recipeQuery));
        Document document = recipes.find(recipeQuery).limit(1).skip(randomIndex).first();
        Recipe recipe = recipeDeserializer.deserialize(document);
        addRecipeIngredients(recipe);
        if (vegan && recipe.getIngredients().stream().anyMatch(food -> food.isVegan() != vegan))
            return getNonRandomRecipe(recipeQuery, vegan); // en el caso de solicitar comidas veganas, si la receta
        return recipe; //  si la receta encontrada no cumple la condicion de ser vegana, aplica el filtro y ya no busca de forma aleatoria
    }

    private Recipe getNonRandomRecipe(Document recipeQuery, boolean vegan) {
        for (Document document : recipes.find(recipeQuery)) { //obtiene la primera receta que cumpla la condicon exigida
            Recipe recipe = recipeDeserializer.deserialize(document);
            addRecipeIngredients(recipe);
            if (vegan && recipe.getIngredients().stream().allMatch(food -> food.isVegan() == vegan))
                return recipe;
        }
        return null;
    }

    private void addRecipeIngredients(Recipe recipe) { //a partir de los ids de la receta, los añade al objeto receta, los objeto de alimentos
        for (String ingredientId : recipe.getIngredientsIds())
            recipe.add(foodDeserializer.deserialize(Objects.requireNonNull(foods.find(new Document("_id", ingredientId)).first())));
    }

    public Food selectOneFood(String food) { //a partir del nombre de una comida lo devuelve
        return foodDeserializer.deserialize(
            Objects.requireNonNull(foods.find(
                new Document("alimento", food)).first()));
    }

    public List<Exercise> selectExercisesFromMuscle(String muscle) {
        // Los funciones que comienzan por selects son funciones sencillas que realizan exactamente lo que pone en su nombre
        MongoCursor<Document> cursor = exercises.find(new Document("musculo", muscle)).iterator();
        List<Exercise> exerciseList = new ArrayList<>();
        while (cursor.hasNext())
            exerciseList.add(exerciseDeserializer.deserialize(cursor.next()));
        return exerciseList;
    }

    public List<Recipe> selectRecipeFromMeal(String comida) {
        MongoCursor<Document> cursor = recipes.find(new Document("comida", comida)).iterator();
        List<Recipe> recipeList = new ArrayList<>();
        while (cursor.hasNext())
            recipeList.add(recipeDeserializer.deserialize(cursor.next()));
        return recipeList;
    }

    public List<Exercise> selectExercisesFromWorkoutDay(String workoutDay, int numEjs, String musculo) {
        Document query = new Document("tipo", workoutDay);
        query.append("musculo", musculo);
        MongoCursor<Document> cursor = exercises.find(query).iterator();
        List<Exercise> exerciseList = new ArrayList<>();
        while (cursor.hasNext() & exerciseList.size() < numEjs)
            exerciseList.add(exerciseDeserializer.deserialize(cursor.next()));
        return exerciseList;
    }

    public List<Exercise> selectExercisesFromWorkoutDay(String workoutDay, int numEjs) {
        Document query = new Document("tipo", workoutDay);
        MongoCursor<Document> cursor = exercises.find(query).iterator();
        List<Exercise> exerciseList = new ArrayList<>();
        while (cursor.hasNext() & exerciseList.size() < numEjs)
            exerciseList.add(exerciseDeserializer.deserialize(cursor.next()));
        return exerciseList;
    }

    public List<Exercise> selectExercisesFromWorkout(String workout, int numEjs) {
        Document query = new Document("tipo_ejs", workout);
        Document document = workouts.find(query).first();
        List<Exercise> exerciseList = new ArrayList<>();
        for (String exerciseId : Objects.requireNonNull(document).getList("lista",String.class)) {
            exerciseList.add(exerciseDeserializer.deserialize(getDocumentFromId(exerciseId)));
            if (exerciseList.size() >= numEjs)
                break;
        }
        return exerciseList;
    }

    private Document getDocumentFromId(String exerciseId) {
        return Objects.requireNonNull(exercises.find(new Document("_id", exerciseId)).first());
    }
}
