package wordle.wordlegame.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wordle.wordlegame.model.intento;

@Repository
public class IntentoRepo implements IIntentoRepo{
    static List<intento> listado = new ArrayList<intento>();
	
	@Override
	public List<intento> getListado() {
		return listado;
	}

	@Override
	public void addListado(intento intento) {
		listado.add(intento);
	}

	@Override
	public void cleanListado() {
		listado.clear();
		
	}
}
