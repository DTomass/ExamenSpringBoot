package wordle.wordlegame.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import wordle.wordlegame.model.palabra;

@Profile("Medio")
@Repository
public class palabraRepoMedio implements IPalabraRepo{

    static List<palabra> listado = new ArrayList<palabra>();
	
	static {
		listado.add(new palabra(1, "Jesus", 5, 10));
		listado.add(new palabra(2, "Maria", 5, 10 ));
        listado.add(new palabra(3, "YJose", 5, 10 ));
		listado.add(new palabra(4, "Kiara", 5, 10));
		listado.add(new palabra(5, "Dylan", 5, 10 ));
        listado.add(new palabra(6, "Danna", 5, 10 ));
		listado.add(new palabra(7, "Elian", 5, 10));
		listado.add(new palabra(8, "Keyla", 5, 10 ));
        listado.add(new palabra(9, "Karen", 5, 10 ));
		listado.add(new palabra(10, "Erick", 5, 10));
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
