package org.ulpgc.es;

import spark.Request;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WebSeviceManager {
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

    private Map<String, String> parametersIn(Request request) {
        Map<String, String> result = request.queryMap().toMap().entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()[0]));
        result.putAll(request.params());
        return result;
    }
}
