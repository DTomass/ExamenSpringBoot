package wordle.wordlegame.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordle.wordlegame.model.Letra;
import wordle.wordlegame.model.palabra;
import wordle.wordlegame.repository.IPalabraRepo;

@Service
public class WordleService extends WordleAbs implements IWordleService{
    @Autowired
	IPalabraRepo repo;

	String palabraDescubrir;
	int intentosPalabra;
    
    @Override
	public void getPalabraAleatoria() {
		List<palabra> lista = repo.getListado();
		int random = new Random().ints(0, 10).findFirst().getAsInt();
		palabra palabra = lista.get(random);
		palabraDescubrir = palabra.getPalabra();
		intentosPalabra = palabra.getIntentos();
	}

    @Override
    public List<Letra> comprobarPalabra(String palabraIntroducida) {
		if (palabraIntroducida.length()==palabraDescubrir.length()) {
			intentosPalabra--;
			return asignarColores(palabraIntroducida, palabraDescubrir);
		}else{
			return null;
		}
        
    }

	@Override
	public int getPalabraIntentos() {
		return intentosPalabra;
	}

	@Override
	public int getPalabraLetras() {
		return palabraDescubrir.length();
	}

	@Override
	public String getPalabraDescubrir() {
		return palabraDescubrir;
	}

	@Override
	public void setRepo(IPalabraRepo palabraRepo) {
		repo = palabraRepo;
	}

	
}
