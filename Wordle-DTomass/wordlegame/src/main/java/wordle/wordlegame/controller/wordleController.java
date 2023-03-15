package wordle.wordlegame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import wordle.wordlegame.model.Letra;
import wordle.wordlegame.model.intento;
import wordle.wordlegame.service.*;



@Controller
public class wordleController {

	@Autowired
	IPalabraService service;
	
	@Autowired
	IWordleService serviceWordle;

	int intentos;
	String palabra;

    @GetMapping("/wordle")
    public ModelAndView index(ModelAndView model) {
		model.setViewName("wordle/wordle");
		return model;
	}
	@PostMapping("/wordleIni")
    public ModelAndView volver(ModelAndView model) {
		model.setViewName("wordle/tabla");
		return model;
	}

	@PostMapping("/wordle/empieza")
    public ModelAndView juego(ModelAndView model) {
		serviceWordle.getPalabraAleatoria();
		palabra = serviceWordle.getPalabraDescubrir();
		intentos=serviceWordle.getPalabraIntentos();
		model.addObject("letras", serviceWordle.getPalabraLetras());
		model.addObject("intentos", intentos);
		serviceWordle.getPalabraAleatoria();
		model.setViewName("wordle/previo");
		return model;
	}
	@PostMapping("/wordle/tablaIni")
    public ModelAndView tabla(ModelAndView model) {
		model.setViewName("wordle/tabla");
		model.addObject("intentos", serviceWordle.getPalabraIntentos());
		return model;
	}
	@PostMapping("/wordle/prueba")
    public ModelAndView ComprobarPalabra(String prueba, ModelAndView model) {
		try{
			service.addIntento(new intento(intentos-serviceWordle.getPalabraIntentos()+1, prueba));
			List<Letra> letras = serviceWordle.comprobarPalabra(prueba);
			int aciertos=0;
			model.addObject("letrotas", letras);
			model.addObject("intentos", serviceWordle.getPalabraIntentos());
			for (Letra letra : letras) {
				if(letra.getColor()==1){
					aciertos++;
				}
			}
			if (serviceWordle.getPalabraIntentos()<=0) {
				model.addObject("palabra", palabra);
				model.setViewName("wordle/mal");
			}else if(aciertos==letras.size()){
				model.addObject("intentotes", intentos-serviceWordle.getPalabraIntentos());
				model.setViewName("wordle/final");
			}else{
				model.setViewName("wordle/tabla");
			}
			
			return model;
		}catch(Exception ex){
			model.setViewName("wordle/error");
			return model;
		}
	}
}