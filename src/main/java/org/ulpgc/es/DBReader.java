package org.ulpgc.es;

import org.ulpgc.es.model.Alimento;

import java.util.List;

public interface DBReader {

    List<Alimento> selectAllAlimentos();
    Alimento selectAllEjercicios();
}