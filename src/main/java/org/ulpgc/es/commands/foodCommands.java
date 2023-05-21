package org.ulpgc.es.commands;

import org.ulpgc.es.Command;
import org.ulpgc.es.model.Client;
import org.ulpgc.es.model.Diet;
import org.ulpgc.es.model.Food;
import org.ulpgc.es.model.Recipe;
import org.ulpgc.es.readers.MongoDBReader;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class foodCommands implements Command {
    /*
    Esta clase es encargada de solventar las peticiones sobre comidas y dietas
     */
    private final MongoDBReader reader = new MongoDBReader();
    @Override
    public String execute(Map<String, String> parameters) {
        /*
        esta es la función principal, para las
        solicitudes sobre comidas, se decide su resultado en base a los parámetros que puso
         */
        if (parameters.containsKey("tipo_dieta"))
            return buildResponse(getDiet(parameters)); // obtiene una dieta
        if (parameters.containsKey("alimento"))
            return buildResponse(getFood(parameters)); //obtiene info sobre un alimento
        if (parameters.containsKey("comida"))
            return buildResponse(getMealRecipes(parameters), parameters.get("comida")); // obtiene alimentos sobre una comida del dia
        return "Siga el manual para poder utilizar correctamente la app.";
    }

    private String buildResponse(List<Recipe> mealFoods, String meal) {
        StringBuilder result = new StringBuilder("Las siguientes recetas pertencen a la comida: \"" + meal + "\"\n\n\n");
        for (Recipe recipe : mealFoods)
            result.append(recipe.toString()).append("\n");
        return result.toString();
    }

    private List<Recipe> getMealRecipes(Map<String, String> parameters) {
        return reader.selectRecipeFromMeal(parameters.get("comida"));
    }

    private String buildResponse(Food food) {
        return food.toString();
    }

    private Food getFood(Map<String, String> parameters) {
        return reader.selectOneFood(parameters.get("alimento"));

    }

    private String buildResponse(Diet diet) {
        return "Se ha solicitado una dieta: " + "\n" + diet.toString();
    }

    private Diet getDiet(Map<String, String> parameters) {
        /*
        A partir de los parámetros crea un objeto cliente que identifica quien y que clase de solicitud realizó
         */
        Client client = new Client(
            Integer.parseInt(parameters.get("peso")),
            Integer.parseInt(parameters.get("altura")),
            parameters.get("tipo_dieta"),
            (parameters.containsKey("vegana") && (Objects.equals(parameters.get("vegana"), "si"))),
            parameters.get("actividad"),
            Integer.parseInt(parameters.get("edad")),
            parameters.get("genero")
        );
        calculateMacronutrients(client); // calcula calorias y proteinas que debe tomar en base a su dieta
        List<Recipe> recipes = calculateFoodRations(reader.selectOneDayDiet(client.isVegan()), client); // calcula las raciones de cada comida
        return createDiet(client, recipes);
    }

    private Diet createDiet(Client client, List<Recipe> recipes) {
        Diet diet = new Diet(recipes.get(0), recipes.get(1), recipes.get(2));
        diet.setCalorieRecommended(client.getCalorieRecommended());
        diet.setProteinRecommended(client.getProteinRecommended());
        diet.setRealCalories(sumCalories(recipes));
        diet.setRealProteins(sumProteins(recipes));
        diet.setClient(client);
        return diet;
    }

    private List<Recipe> calculateFoodRations(List<Recipe> recipes, Client client) {
        getEnoughProtein(recipes, client); //suficientes proteinas
        getEnoughCalories(recipes, client); //suficientes calorias
        getNotExccessiveCalorieIntake(recipes, client); // reducir calorias si fueron demasiadas
        return recipes;
    }

    private void getEnoughProtein(List<Recipe> recipes, Client client) {
        while (sumProteins(recipes) < client.getProteinRecommended()) {
            for (Recipe recipe : recipes) {
                for (Food ingredient : getIngredientsHighProtein(recipe))  {
                    ingredient.increaseRacion(); // para añadir mas proteina, aumentamos cantidad de comida en ingredientes
                }                                  // altos en proteina
            }
        }
    }

    private List<Food> getIngredientsHighProtein(Recipe recipe) {
        return recipe.getIngredients().stream().filter(this::isHighProtein).collect(Collectors.toList());
    }

    private boolean isHighProtein(Food food) {
        if (food.getProvide() == null)
            return true;
        return !Objects.equals(food.getProvide(), "carbohidratos");
    }

    private double sumProteins(List<Recipe> recipes) {
        return recipes.stream()
            .map(recipe -> recipe.getIngredients().stream().map(foodCommands::calculateFoodProtein)
                .reduce((double) 0, Double::sum))
            .reduce((double) 0, Double::sum);
    }

    private static double calculateFoodProtein(Food food) {
        return ((double) food.getProteinsPer100g() / 100) * food.getRation();
    }
    private void getNotExccessiveCalorieIntake(List<Recipe> recipes, Client client) {
        while (!notExccessiveCalorieIntake(recipes, client)) {
            for (Recipe recipe : recipes) {
                for (Food ingredient : getIngredientsNotHighProtein(recipe))  {
                    ingredient.decreaseRacion(); //para aumentar o reducir calorias, modificamos las cantidades de los
                }                                //alimentos que no sean especialmente altos en proteinas
            }
        }
    }

    private void getEnoughCalories(List<Recipe> recipes, Client client) {
        while (!enoughCalorieIntake(recipes, client)) {
            for (Recipe recipe : recipes) {
                for (Food ingredient : getIngredientsNotHighProtein(recipe))  {
                    ingredient.increaseRacion();
                }
            }
        }
    }

    private List<Food> getIngredientsNotHighProtein(Recipe recipe) {
        return recipe.getIngredients().stream().filter(this::notHighProtein).collect(Collectors.toList());
    }

    private boolean notHighProtein(Food food) {
        return !Objects.isNull(food.getProvide()) & Objects.equals(food.getProvide(), "carbohidratos");
    }

    private boolean enoughCalorieIntake(List<Recipe> recipes, Client client) {
        return sumCalories(recipes) >= client.getCalorieRecommended() - 200;
    }

    private boolean notExccessiveCalorieIntake(List<Recipe> recipes, Client client) {
        return sumCalories(recipes) <= client.getCalorieRecommended() + 200;
    }

    private double sumCalories(List<Recipe> recipes) {
        return recipes.stream()
            .map(recipe -> recipe.getIngredients().stream().map(foodCommands::calculateFoodCalories)
                .reduce((double) 0, Double::sum))
            .reduce((double) 0, Double::sum);

    }

    private static double calculateFoodCalories(Food food) {
        return ((double) food.getCaloriesPer100g() / 100) * food.getRation();
    }

    private void calculateMacronutrients(Client client) {
        double BMR = calculateMaintainCalories(client);
        if (Objects.equals(client.getDiet(), "volumen"))
            client.setCalorieRecommended((int) (BMR + 300));
        if (Objects.equals(client.getDiet(), "definicion"))
            client.setCalorieRecommended((int) (BMR - 300));
        client.setProteinRecommended(client.getWeight() * 2);
    }

    private static double calculateMaintainCalories(Client client) {
        double BMR = 0;
        if (client.getGender().equals("masculino"))
            BMR = 10 * client.getWeight() + 6.25 * client.getHeight() - 5 * client.getAge() + 5;
        if (client.getGender().equals("femenino"))
            BMR = 10 * client.getWeight() + 6.25 * client.getHeight() - 5 * client.getAge() - 161;
        if (Objects.equals(client.getActivity(), "poca"))
            BMR = BMR * 1.2;
        if (Objects.equals(client.getActivity(), "media"))
            BMR = BMR * 1.55;
        if (Objects.equals(client.getActivity(), "alta"))
            BMR = BMR * 1.725;
        return BMR;
    }
}
