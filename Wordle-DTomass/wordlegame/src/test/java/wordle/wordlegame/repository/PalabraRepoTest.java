package wordle.wordlegame.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;

import org.junit.jupiter.api.Test;

import wordle.wordlegame.model.palabra;

public class PalabraRepoTest {
    IPalabraRepo palabraRepo = new palabraRepoDificil();

    @Test
    void getListadoTest(){
        List<palabra> palabras = palabraRepo.getListado();
        assertNotNull(palabras);
        assertEquals(10, palabras.size());
        assertEquals("Santiago", palabraRepo.getListado().get(0).getPalabra());
    }

    @Test
    void getPalabraByIdTest(){
        palabra palabraEsperada = new palabra(5, "Leonardo", 8, 6);
        palabra palabraObtenida = palabraRepo.getPalabraById(palabraEsperada.getId());
        assertNotNull(palabraObtenida);
        assertEquals(palabraEsperada.getId(), palabraObtenida.getId());
        assertEquals(palabraEsperada.getPalabra(), palabraObtenida.getPalabra());
        assertEquals(palabraEsperada.getIntentos(), palabraObtenida.getIntentos());
        assertEquals(palabraEsperada.getIntentos(), palabraObtenida.getIntentos());
        palabra palabraMal = palabraRepo.getPalabraById(0);
        assertNotNull(palabraMal);
    }
}