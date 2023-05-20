package org.ulpgc.es;

import org.ulpgc.es.commands.exerciseCommands;
import org.ulpgc.es.commands.foodCommands;

public class Main {
    public static void main(String[] args) {
        WebSeviceManager webService = new WebSeviceManager();

        webService.add("/ejercicios", new exerciseCommands());

        webService.add("/dietas", new foodCommands());

        webService.start();
    }
}
