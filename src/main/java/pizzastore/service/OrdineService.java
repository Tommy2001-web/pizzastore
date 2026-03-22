package pizzastore.service;

import pizzastore.model.Ordine;

import java.util.List;

public interface OrdineService {
    public List<Ordine> listAllElements();

    public Ordine caricaSingoloElemento(Long id);

    public Ordine caricaSingoloElementoEager(Long id);

    public void aggiorna(Ordine ordineInstance);

    public void inserisciNuovo(Ordine ordineInstance);

    public void disattiva(Long idOrdine);

    public List<Ordine> findByExample(Ordine example);

    public Double calcolaPrezzoOrdine(Ordine ordine);
}
