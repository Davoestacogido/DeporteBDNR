package org.ulpgc.es;

import spark.Request;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WebSeviceManager {

    /*
    Esta clase es la encargada de manejar el sitio web, define como se guardaran los comandos, que en este caso será
    mediante mapas, cuando nos hagan una solicitud, obtendremos los comandos en formato clave valor, perfecto para el mapa
     */
    private final Map<String, Command> commands;

    public WebSeviceManager() {
        this.commands = new HashMap<>();
    }

    public void add(String route, Command command) {
        commands.put(route, command);
    }

    public void start() {
        Spark.port(8080);
        for (String route : commands.keySet()) {
            Spark.get(route, (req, res) -> {
                String result = commands.get(route).execute(parametersIn(req));
                res.status(200);
                res.type("text/plain");
                return result;
            });
        }
    }

    private Map<String, String> parametersIn(Request request) { // esta función recoge los parámetros de lo que llegue por la URL
        Map<String, String> result = request.queryMap().toMap().entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()[0]));
        result.putAll(request.params());
        return result;
    }
}
