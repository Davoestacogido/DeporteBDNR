package org.ulpgc.es.commands;

import org.ulpgc.es.Command;

import java.util.Map;

public class exerciseCommands implements Command {
    @Override
    public String execute(Map<String, String> parameters) {
        String musculo = parameters.get("musculo");
        String tipoEjercicio = parameters.get("tipo_ejercicio");
        int numElementos = getNumElementos(parameters);
        return x(musculo, tipoEjercicio, numElementos);
    }

    private String x(String musculo, String tipoEjercicio, int numElementos) {
        StringBuilder response = builder(musculo, tipoEjercicio, numElementos);

        // Verificar si se pudo construir la respuesta
        if (response.length() == 0) {
            return "Not found (Error 404)";
        }

        return response.toString();
    }

    static StringBuilder builder(String musculo, String tipoEjercicio, int numElementos) {
        StringBuilder response = new StringBuilder("Conectado al apartado de ejercicios");

        if (musculo != null) {
            response.append(" por músculo: ").append(musculo);
        }

        if (tipoEjercicio != null) {
            response.append(" por tipo de ejercicio: ").append(tipoEjercicio);
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
