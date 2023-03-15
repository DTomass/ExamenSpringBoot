package wordle.wordlegame.repository;

import wordle.wordlegame.model.intento;
import java.util.List;

public interface IIntentoRepo {
    List<intento> getListado();
	void addListado(intento intento);
    void cleanListado();
}
