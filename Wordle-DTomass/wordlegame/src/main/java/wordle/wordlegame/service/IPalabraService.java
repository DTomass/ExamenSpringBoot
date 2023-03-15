package wordle.wordlegame.service;

import java.util.List;

import wordle.wordlegame.model.*;

public interface IPalabraService {
    List<intento> getAll();

    intento search(int busqueda);

    void addIntento(intento intento);

    void cleanIntentos();
}
