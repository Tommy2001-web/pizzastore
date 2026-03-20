package raccoltafilmspringmvc.repository.regista;

import it.prova.raccoltafilmspringmvc.model.Regista;

import java.util.List;

public interface CustomRegistaRepository {
	List<Regista> findByExample(Regista example);
}
