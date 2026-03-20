package raccoltafilmspringmvc.service;

import it.prova.raccoltafilmspringmvc.model.Film;

import java.util.List;

public interface FilmService {
	public List<Film> listAllElements();

	public Film caricaSingoloElemento(Long id);
	
	public Film caricaSingoloElementoEager(Long id);

	public void aggiorna(Film filmInstance);

	public void inserisciNuovo(Film filmInstance);

	public void rimuovi(Long idFilmToDelete);

	public List<Film> findByExample(Film example);

}
