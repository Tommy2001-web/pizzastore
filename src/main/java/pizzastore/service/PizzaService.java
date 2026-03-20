package pizzastore.service;

import pizzastore.model.Pizza;

import java.util.List;

public interface PizzaService {
    public List<Pizza> listAllElements();

    public Pizza caricaSingoloElemento(Long id);

    public Pizza caricaSingoloElementoEager(Long id);

    public void aggiorna(Pizza pizzaInstance);

    public void inserisciNuovo(Pizza pizzaInstance);

    public void rimuovi(Long idPizza);

    public List<Pizza> findByExample(Pizza example);
}
