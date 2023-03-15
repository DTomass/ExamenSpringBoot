package wordle.wordlegame.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.ModelAndView;

import wordle.wordlegame.model.intento;

@WebMvcTest(palabrasController.class)
@AutoConfigureMockMvc

public class PalabraControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGoToIndex() throws Exception {
        MvcResult result = mockMvc.perform(get("/palabras")).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("palabras/buscaPalabras", model.getViewName());
    }

    @Test
    void testGoToMenu() throws Exception {
        MvcResult result = mockMvc.perform(get("/menuIni")).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("index", model.getViewName());
    }

    @Test
    void testBuscaPalabra() throws Exception {
        MockHttpServletRequestBuilder form = post("/palabra/busqueda").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("busqueda", "1");
        MvcResult result = mockMvc.perform(form).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("palabras/buscaPalabras", model.getViewName());
        assertTrue(model.getModel().keySet().contains("palabra"));
        intento intento = (intento) model.getModel().get("palabra");
        assertNotNull(intento);
    }

    @Test
    void testBuscaTodas() throws Exception {
        MvcResult result = mockMvc.perform(post("/palabra/busquedaTotal")).andExpect(status().isOk()).andReturn();
        ModelAndView model = result.getModelAndView();
        assertEquals("palabras/buscaPalabras", model.getViewName());
        assertTrue(model.getModel().keySet().contains("palabra"));
        List<intento> intento = (List<intento>) model.getModel().get("palabra");
        assertNotNull(intento);
    }
}
