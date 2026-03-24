package pizzastore.service;

import pizzastore.dto.StatsDTO;
import pizzastore.model.Ordine;

import java.time.LocalDate;
import java.util.List;

public interface OrdineService {
    public List<Ordine> listAllElements();

    public Ordine caricaSingoloElemento(Long id);

    public Ordine caricaSingoloElementoEager(Long id);

    public void aggiorna(Ordine ordineInstance);

    public void inserisciNuovo(Ordine ordineInstance);

    public void toggleAttivo(Long idOrdine);

    public List<Ordine> findByExample(Ordine example);

    public Double calcolaPrezzoOrdine(Ordine ordine);

    public StatsDTO calcolaStatistiche(LocalDate dataInizio, LocalDate dataFine);

    public Double calcolaSconto(double totale, int numeroOrdini);
}
