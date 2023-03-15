package wordle.wordlegame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import wordle.wordlegame.service.*;



@Controller
public class palabrasController {

    @Autowired
	IPalabraService service;

    @GetMapping("/palabras")
    public ModelAndView index(ModelAndView model) {
		model.setViewName("palabras/buscaPalabras");
		return model;
	}

    @GetMapping("/menuIni")
    public ModelAndView menu(ModelAndView model) {
		model.setViewName("index");
		service.cleanIntentos();
		return model;
	}

    @PostMapping("/palabra/busqueda")
    public ModelAndView buscaPalabras(int busqueda, ModelAndView model) {
		model.addObject("palabra", service.search(busqueda));
		model.setViewName("palabras/buscaPalabras");
		return model;
	}
	@PostMapping("/palabra/busquedaTotal")
    public ModelAndView buscaTodas(ModelAndView model) {
		model.addObject("palabra", service.getAll());
		model.setViewName("palabras/buscaPalabras");
		return model;
	}
}
