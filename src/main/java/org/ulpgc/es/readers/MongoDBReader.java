package org.ulpgc.es.readers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.ulpgc.es.DBReader;
import org.ulpgc.es.model.Alimento;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class MongoDBReader implements DBReader {

    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = mongoClient.getDatabase("deporte");
    MongoCollection<Document> alimentos = database.getCollection("dieta");

    public List<Alimento> selectAllAlimentos() {
        for (Document document : alimentos.find()) {
            System.out.println(document.toJson());
        }
        return null;
    }

    public Alimento selectAllEjercicios() {
        return null;
    }
}
