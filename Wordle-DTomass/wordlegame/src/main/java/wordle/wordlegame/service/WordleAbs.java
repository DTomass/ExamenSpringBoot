package wordle.wordlegame.service;

import java.util.ArrayList;
import java.util.List;

import wordle.wordlegame.model.Letra;

public abstract class WordleAbs {
    public List<Letra> asignarColores(String palabraIntroducida, String palabraDescubrir) {
		List<Letra> letras = new ArrayList<>();
		for (int i=0; i<palabraDescubrir.length(); i++) {
			letras.add(comprobarColor(palabraIntroducida.charAt(i), palabraDescubrir.charAt(i), palabraDescubrir));
		}
		return letras;
	}

	public Letra comprobarColor(char letraIntroducida, char letraDescubrir, String palabraDescubrir) {
		Letra letra;
		if(Character.toUpperCase(letraDescubrir)==Character.toUpperCase(letraIntroducida)){
			letra = new Letra(Character.toUpperCase(letraIntroducida), 1);
			return letra;
		}else if (palabraDescubrir.contains(Character.toString(letraIntroducida))){
			letra=new Letra(Character.toUpperCase(letraIntroducida), 2);
			return letra;
		}
		else{
			letra=new Letra(Character.toUpperCase(letraIntroducida), 3);
			return letra;
		}
	}
}
