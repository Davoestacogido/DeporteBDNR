package org.ulpgc.es;

import org.ulpgc.es.commands.exerciseCommands;
import org.ulpgc.es.commands.foodCommands;
import org.ulpgc.es.readers.MongoDBReader;

public class Main {
    public static void main(String[] args) {

        WebSeviceManager webService = new WebSeviceManager();

        webService.add("/ejercicios", new exerciseCommands());

        webService.add("/dietas", new foodCommands());

        webService.start();


        MongoDBReader reader =  new MongoDBReader();
        reader.selectOneDayDiet(true);
    }
}
