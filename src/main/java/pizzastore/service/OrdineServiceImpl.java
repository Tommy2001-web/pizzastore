package pizzastore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzastore.model.Cliente;
import pizzastore.model.Ordine;
import pizzastore.repository.ordine.OrdineRepository;

import java.util.List;

@Transactional
@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Override
    public List<Ordine> listAllElements() {
        return ordineRepository.findAll();
    }

    @Override
    public Ordine caricaSingoloElemento(Long id) {
        return ordineRepository.findById(id).orElse(null);
    }

    @Override
    public Ordine caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    public void inserisciNuovo(Ordine ordineInstance) {
        ordineRepository.save(ordineInstance);
    }

    @Override
    public void aggiorna(Ordine ordineInstance) {
        ordineRepository.save(ordineInstance);
    }

    @Override
    public void disattiva(Long id) {
        ordineRepository.deleteById(id);
    }

    @Override
    public List<Ordine> findByExample(Ordine example) {
        return ordineRepository.findByExample(example);
    }

    @Override
    public Double calcolaPrezzoOrdine(Ordine ordine) {
        return 0.0;
    }
}
