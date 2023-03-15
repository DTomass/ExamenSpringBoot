package wordle.wordlegame.repository;

import wordle.wordlegame.model.palabra;
import java.util.List;

public interface IPalabraRepo {
    List<palabra> getListado();
    palabra getPalabraById(int id);
}
