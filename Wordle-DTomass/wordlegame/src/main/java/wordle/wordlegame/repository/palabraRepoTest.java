package wordle.wordlegame.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import wordle.wordlegame.model.palabra;

@Profile("Test")
@Repository
public class palabraRepoTest implements IPalabraRepo{
    static List<palabra> listado = new ArrayList<palabra>();
	
	static {
		
		listado.add(new palabra(1, "Santiago", 8 , 6));
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
