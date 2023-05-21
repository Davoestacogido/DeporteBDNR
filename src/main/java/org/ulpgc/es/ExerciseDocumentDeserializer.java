package org.ulpgc.es;

import org.bson.Document;
import org.ulpgc.es.model.Exercise;

public interface ExerciseDocumentDeserializer {
    /*
Interfaz para implementaciones de deserializador de ejercicios, pasa de un documento de MongoDB a objeto de java
 las interfaces son muy útiles para crear un código más flexible y escalable
 */
    Exercise deserialize(Document exerciseDocument);
}
