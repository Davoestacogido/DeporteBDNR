package org.ulpgc.es.commands;

import org.ulpgc.es.Command;

import java.util.Map;

public class foodCommands implements Command {
    @Override
    public String execute(Map<String, String> parameters) {
        String tipoDieta = parameters.get("tipo_dieta");
        String comida = parameters.get("comida");
        int numElementos = getNumElementos(parameters);
        return generarRespuesta(tipoDieta, comida, numElementos);
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
