package wordle.wordlegame.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import wordle.wordlegame.model.intento;

public class IntentoRepoTest {
    IIntentoRepo intentoRepo = new IntentoRepo();

    @Test
    void getListadoTest(){
        List<intento> intentos = intentoRepo.getListado();
        assertNotNull(intentos);
    }

    @Test
	void addListadoTest() {
        intentoRepo.getListado().clear();
        intento intento = new intento(1, "hola");
        assertEquals(0, intentoRepo.getListado().size());
		intentoRepo.addListado(intento);
        assertEquals(1, intentoRepo.getListado().size());
        assertNotNull(intentoRepo.getListado().get(0));
        assertEquals(intento, intentoRepo.getListado().get(0));
        intentoRepo.cleanListado();
    }

    @Test
	void cleanListadoTest() {
        intentoRepo.getListado().clear();
        intento intento = new intento(1, "hola");
        assertEquals(0, intentoRepo.getListado().size());
		intentoRepo.addListado(intento);
        assertEquals(1, intentoRepo.getListado().size());
        assertNotNull(intentoRepo.getListado().get(0));
        intentoRepo.cleanListado();
        assertEquals(0, intentoRepo.getListado().size());
    }
}
