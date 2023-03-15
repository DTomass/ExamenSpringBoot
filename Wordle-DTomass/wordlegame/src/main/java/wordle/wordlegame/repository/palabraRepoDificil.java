package wordle.wordlegame.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import wordle.wordlegame.model.palabra;

@Profile("Dificil")
@Repository
public class palabraRepoDificil implements IPalabraRepo{
    static List<palabra> listado = new ArrayList<palabra>();
	
	static {
		
		listado.add(new palabra(1, "Santiago", 8 , 6));
		listado.add(new palabra(2, "Josefina", 8, 6 ));
		listado.add(new palabra(3, "Samantha", 8 , 6));
		listado.add(new palabra(4, "Valentín", 8, 6 ));
		listado.add(new palabra(5, "Leonardo", 8 , 6));
		listado.add(new palabra(6, "Mauricio", 8, 6 ));
		listado.add(new palabra(7, "Victoria", 8 , 6));
		listado.add(new palabra(8, "Rasputin", 8, 6 ));
		listado.add(new palabra(9, "Milagros", 8 , 6));
		listado.add(new palabra(10, "Macarena", 8, 6 ));
	}
	
	@Override
	public List<palabra> getListado() {
		return listado;
	}

	@Override
	public palabra getPalabraById(int id) {
		palabra palabra = new palabra();
		for(int i=0; i<listado.size(); i++){
			if(listado.get(i).getId() == id){
				palabra=listado.get(i);
			}
		}
		return palabra;
	}
}
