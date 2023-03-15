package wordle.wordlegame.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.ModelAndView;

@WebMvcTest(wordleController.class)
@AutoConfigureMockMvc

public class WordleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGoToIndex() throws Exception {
        MvcResult result = mockMvc.perform(get("/wordle")).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("wordle/wordle", model.getViewName());
    }

    @Test
    void testVolver() throws Exception {
        MvcResult result = mockMvc.perform(post("/wordleIni")).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("wordle/tabla", model.getViewName());
    }

    @Test
    void testJuego() throws Exception {
        MvcResult result = mockMvc.perform(post("/wordle/empieza")).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("wordle/previo", model.getViewName());
        assertTrue(model.getModel().keySet().contains("letras"));
        assertTrue(model.getModel().keySet().contains("intentos"));
        int letras = (int) model.getModel().get("letras");
		int intentos = (int) model.getModel().get("intentos");
		assertNotNull(letras);
        assertNotNull(intentos);
    }

    @Test
    void testTabla() throws Exception {
        MvcResult result = mockMvc.perform(post("/wordle/tablaIni")).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("wordle/tabla", model.getViewName());
        assertTrue(model.getModel().keySet().contains("intentos"));
        int intentos = (int) model.getModel().get("intentos");
        assertNotNull(intentos);
    }

	@Test
    void testComprobarPalabraFallo() throws Exception {
		MockHttpServletRequestBuilder formAcierto = post("/wordle/prueba").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("prueba", "");
        MvcResult result = mockMvc.perform(formAcierto).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("wordle/error", model.getViewName());    
	}
}
