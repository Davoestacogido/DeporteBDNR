package org.ulpgc.es;

import org.bson.Document;
import org.ulpgc.es.model.Exercise;

public interface ExerciseDocumentDeserializer {
    Exercise deserialize(Document exerciseDocument);
}
