package wordle.wordlegame.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wordle.wordlegame.model.Letra;
import wordle.wordlegame.repository.palabraRepoDificil;

public class WordleServiceTest {
    
    @Autowired
    WordleService wordleService = new WordleService();
    
    @Test
    void getPalabraAleatoriaTest(){
        wordleService.setRepo(new palabraRepoDificil());
        assertNull(wordleService.getPalabraDescubrir());
        wordleService.getPalabraAleatoria();
        assertNotNull(wordleService.getPalabraDescubrir());
    }

    @Test
    void comprobarPalabraCorrectaTest(){
        wordleService.setRepo(new palabraRepoDificil());
        wordleService.getPalabraAleatoria();
        String palabra = wordleService.getPalabraDescubrir();
        List<Letra> letras = wordleService.comprobarPalabra(palabra);
        assertNotNull(letras);
        for (int i=0; i<letras.size(); i++) {
            assertEquals(Character.toUpperCase(palabra.charAt(i)), letras.get(i).getLetra());
            assertEquals(1, letras.get(i).getColor());
        }
    }

    @Test
    void getPalabraIntentosTest(){
        int intentosPalabra = wordleService.getPalabraIntentos();
        assertNotNull(intentosPalabra);
    }

    @Test
    void getPalabraLetrasTest(){
        int letrasPalabra = wordleService.getPalabraIntentos();
        assertNotNull(letrasPalabra);
    }

    @Test
    void getPalabraDescubrirTest(){
        wordleService.setRepo(new palabraRepoDificil());
        wordleService.getPalabraAleatoria();
        String intentosPalabra = wordleService.getPalabraDescubrir();
        assertNotNull(intentosPalabra);
    }
}
