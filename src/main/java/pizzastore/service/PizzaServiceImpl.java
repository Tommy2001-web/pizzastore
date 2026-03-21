package pizzastore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzastore.model.Pizza;
import pizzastore.repository.pizza.PizzaRepository;

import java.util.List;

@Service
@Transactional
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;


    @Override
    public List<Pizza> listAllElements() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza caricaSingoloElemento(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    @Override
    public Pizza caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    public void aggiorna(Pizza pizzaInstance) {

    }

    @Override
    public void inserisciNuovo(Pizza clienteInstance) {

    }

    @Override
    public void rimuovi(Long idPizza) {

    }

    @Override
    public List<Pizza> findByExample(Pizza example) {

        return List.of();
    }
}
