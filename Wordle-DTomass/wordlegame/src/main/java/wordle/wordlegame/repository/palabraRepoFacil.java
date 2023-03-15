package wordle.wordlegame.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import wordle.wordlegame.model.palabra;

@Profile("Facil")
@Repository
public class palabraRepoFacil implements IPalabraRepo{
    static List<palabra> listado = new ArrayList<palabra>();
	
	static {
		listado.add(new palabra(1, "Mar", 3, 20));
		listado.add(new palabra(2, "Leo", 3, 20 ));
		listado.add(new palabra(3, "Zoe", 3, 20));
		listado.add(new palabra(4, "Pol", 3, 20 ));
		listado.add(new palabra(5, "Teo", 3, 20));
		listado.add(new palabra(6, "Luz", 3, 20 ));
		listado.add(new palabra(7, "Sia", 3, 20));
		listado.add(new palabra(8, "Nel", 3, 20 ));
		listado.add(new palabra(9, "Lua", 3, 20));
		listado.add(new palabra(10, "Roi", 3, 20 ));
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
