package wordle.wordlegame.service;

import java.util.List;

import wordle.wordlegame.model.Letra;
import wordle.wordlegame.repository.IPalabraRepo;

public interface IWordleService {
    void getPalabraAleatoria();
    List<Letra> comprobarPalabra(String palabraIntroducida);
    int getPalabraIntentos();
    int getPalabraLetras();
    String getPalabraDescubrir();
    void setRepo(IPalabraRepo palabraRepo);
}
