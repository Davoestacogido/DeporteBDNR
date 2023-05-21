package org.ulpgc.es.commands;

import org.ulpgc.es.Command;
import org.ulpgc.es.model.Client;
import org.ulpgc.es.model.Recipe;
import org.ulpgc.es.readers.MongoDBReader;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class foodCommands implements Command {

    private final MongoDBReader reader = new MongoDBReader();
    @Override
    public String execute(Map<String, String> parameters) {
        String tipoDieta = parameters.get("tipo_dieta");
        String comida = parameters.get("comida");
        if (parameters.containsKey("tipo_dieta")) {
            List<Recipe> recipes = getDiet(parameters);
        }
        int numElementos = getNumElementos(parameters);
        return generarRespuesta(tipoDieta, comida, numElementos);
    }

    private List<Recipe> getDiet(Map<String, String> parameters) {
        Client client = new Client(
            Integer.parseInt(parameters.get("peso")),
            Integer.parseInt(parameters.get("altura")),
            parameters.get("dieta"),
            (parameters.containsKey("vegana") && (Objects.equals(parameters.get("vegana"), "vegana"))),
            parameters.get("actividad"),
            Integer.parseInt(parameters.get("edad")),
            parameters.get("género")
        );
        calculateMacronutrients(client);
        return calculateFoodRations(reader.selectOneDayDiet(client.isVegan()), client);
    }

    private List<Recipe> calculateFoodRations(List<Recipe> selectOneDayDiet, Client client) {
        return null;
    }

    private void calculateMacronutrients(Client client) {
        double BMR = calculateMaintainCalories(client);
        if (Objects.equals(client.getDiet(), "volumen"))
            client.setCalorieRecommended((int) (BMR + 300));
        if (Objects.equals(client.getDiet(), "definicion"))
            client.setCalorieRecommended((int) (BMR - 200));
        client.setProteinRecommended(client.getWeight() * 2);
    }

    private static double calculateMaintainCalories(Client client) {
        double BMR = 0;
        if (client.getGender().equals("masculino"))
            BMR = 10 * client.getWeight() + 6.25 * client.getHeight() - 5 * client.getAge() + 5;
        if (client.getGender().equals("femenino"))
            BMR = 10 * client.getWeight() + 6.25 * client.getHeight() - 5 * client.getAge() - 161;
        if (Objects.equals(client.getActivity(), "Poca"))
            BMR = BMR * 1.2;
        if (Objects.equals(client.getActivity(), "Media"))
            BMR = BMR * 1.55;
        if (Objects.equals(client.getActivity(), "Alta"))
            BMR = BMR * 1.725;
        return BMR;
    }

    private String generarRespuesta(String tipoDieta, String comida, int numElementos) {
        StringBuilder response = builder(tipoDieta, comida, numElementos);

        // Verificar si se pudo construir la respuesta
        if (response.length() == 0) {
            return "Not found (Error 404)";
        }

        return response.toString();
    }

    static StringBuilder builder(String tipoDieta, String comida, int numElementos) {
        StringBuilder response = new StringBuilder("Conectado al apartado de comidas de dieta");

        if (tipoDieta != null) {
            response.append(" por tipo de dieta: ").append(tipoDieta);
        }

        if (comida != null) {
            response.append(" por tipo de comida: ").append(comida);
        }

        if (numElementos > 0) {
            response.append("\nNúmero de elementos: ").append(numElementos);
        }
        return response;
    }

    private static int getNumElementos(Map<String, String> parameters) {
        String numElementosStr = parameters.get("num_elementos");
        if (numElementosStr != null) {
            try {
                return Integer.parseInt(numElementosStr);
            } catch (NumberFormatException e) {
                // Manejo de excepción si no se puede convertir a entero
            }
        }
        return 0;
    }
}
