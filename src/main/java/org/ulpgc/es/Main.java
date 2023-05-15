package org.ulpgc.es;

import org.ulpgc.es.readers.MongoDBReader;

public class Main {
    public static void main(String[] args) {
        System.out.println(new MongoDBReader().selectAllAlimentos());
    }
}
