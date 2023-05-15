package org.ulpgc.es;

import org.ulpgc.es.model.Alimento;

public interface DBReader {

    Alimento selectAllAlimentos();
    Alimento selectAllEjercicios();
}