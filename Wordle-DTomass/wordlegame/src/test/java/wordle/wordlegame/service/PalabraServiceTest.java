package wordle.wordlegame.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import wordle.wordlegame.model.intento;

public class PalabraServiceTest {
    IPalabraService palabraService = new PalabraService();
    
    @Test
    void getAllTest(){
        List<intento> intentos = palabraService.getAll();
        assertNotNull(intentos);
        assertEquals(0, intentos.size());
        palabraService.getAll().clear();
    }

    @Test
    void addIntentoTest(){
        intento intento = new intento(1, "hola");
        palabraService.addIntento(intento);
        assertEquals(1, palabraService.getAll().size());
        assertEquals(intento.getPalabra(), palabraService.getAll().get(0).getPalabra());
        palabraService.getAll().clear();
    }

    @Test
    void searchTest(){
        palabraService.addIntento(new intento(1, "hola"));
        intento intento1 = palabraService.search(1);
        assertNotNull(intento1);
        assertEquals("hola", intento1.getPalabra());
        intento intento2 = palabraService.search(0);
        assertNotNull(intento2);
        palabraService.getAll().clear();
    }

    @Test
    void cleanIntentosTest(){
        assertEquals(0, palabraService.getAll().size());
        intento intento = new intento(1, "hola");
        palabraService.addIntento(intento);
        assertEquals(1, palabraService.getAll().size());
        palabraService.cleanIntentos();
        assertEquals(0, palabraService.getAll().size());
    }
}
