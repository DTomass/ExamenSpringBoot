package wordle.wordlegame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordle.wordlegame.model.intento;
import wordle.wordlegame.repository.IIntentoRepo;
import wordle.wordlegame.repository.IntentoRepo;

@Service
public class PalabraService implements IPalabraService{
    
	@Autowired
	private IIntentoRepo repo = new IntentoRepo();

	@Override
	public List<intento> getAll() {
		return repo.getListado();
	}

    @Override
    public intento search(int busqueda) {
		intento palabraFiltrada = new intento();
        if ((busqueda != 0)) {
			for (intento p : repo.getListado()) {
				if (p.getNumero() == busqueda) {					
					palabraFiltrada = p;
				}
			}
			return palabraFiltrada;
		}
		else {
			return palabraFiltrada;
		}
    }

	@Override
    public void addIntento(intento intento) {
        repo.addListado(intento);
    }

	@Override
	public void cleanIntentos() {
		repo.cleanListado();
	}
}
