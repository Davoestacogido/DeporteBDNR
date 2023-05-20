package org.ulpgc.es.readers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.ulpgc.es.DBReader;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.ulpgc.es.model.Exercise;
import org.ulpgc.es.model.Food;

import java.util.List;

public class MongoDBReader implements DBReader {

    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = mongoClient.getDatabase("deporte");
    MongoCollection<Document> foods = database.getCollection("dieta");

    public List<Food> selectAllFood() {
        for (Document document : foods.find()) {
            System.out.println(document.toJson());
        }
        return null;
    }

    public List<Exercise> selectAllExercises() {
        return null;
    }
}
