package org.ulpgc.es;
import java.util.Map;

public interface Command {
    /*
    Interfaz para implementaciones de comandos, las interfaces son muy útiles para crear un código más flexible y escalable
     */
    String execute(Map<String, String> parameters);
}