package wordle.wordlegame.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wordle.wordlegame.model.Letra;

public class WordleAbsTest {
    @Autowired
    WordleService wordleService = new WordleService();

    @Test
    void asignarColoresTest(){
        List<Letra> letras = wordleService.asignarColores("hola", "aloh");
        assertNotNull(letras);
    }

    @Test
    void comprobarColorTest(){
        String palabra = "lola";
        Letra letraVerde = wordleService.comprobarColor('l', 'l', palabra);
        assertNotNull(letraVerde);
        assertEquals(1, letraVerde.getColor());
        Letra letraAmarilla = wordleService.comprobarColor('o', 'l', palabra);
        assertNotNull(letraAmarilla);
        assertEquals(2, letraAmarilla.getColor());
        Letra letraRoja = wordleService.comprobarColor('f', 'l', palabra);
        assertNotNull(letraRoja);
        assertEquals(3, letraRoja.getColor());
    }
}
