package pizzastore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzastore.dto.ClienteDTO;
import pizzastore.dto.StatsDTO;
import pizzastore.model.Ordine;
import pizzastore.model.Pizza;
import pizzastore.repository.ordine.OrdineRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

        Ordine ordine = ordineRepository.findById(id).orElse(null);
        if (ordine != null) {
            ordine.getPizze().size();
        }
        return ordine;

    }

    @Override
    public void inserisciNuovo(Ordine ordineInstance) {
        ordineInstance.setClosed(false);
        ordineRepository.save(ordineInstance);
    }

    @Override
    public void aggiorna(Ordine ordineInstance) {
        ordineRepository.save(ordineInstance);
    }

    @Override
    public void toggleAttivo(Long idOrdine) {
        ordineRepository.findById(idOrdine).ifPresent(ordineInstance ->
                ordineInstance.setClosed(!ordineInstance.getClosed())
        );
    }

    @Override
    public List<Ordine> findByExample(Ordine example) {
        return ordineRepository.findByExample(example);
    }

    @Override
    public Double calcolaPrezzoOrdine(Ordine ordine) {
        if (ordine.getPizze() == null || ordine.getPizze().isEmpty()) {
            return 0.0;
        }

        Double sommaTotale = ordine.getPizze()
                .stream()
                .map(Pizza::getPrezzoBase)
                .filter(p -> p != null)
                .reduce(0.0, Double::sum);
        sommaTotale += ((sommaTotale / 100) * 15);
        return sommaTotale;
    }

    @Override
    public StatsDTO calcolaStatistiche(LocalDate dataInizio, LocalDate dataFine) {

        List<Ordine> ordini = ordineRepository.findByDataOrdineBetween(
                dataInizio != null ? dataInizio.atStartOfDay(): null,
                dataFine != null ? dataFine.atTime(23,59,59): null
        );

        StatsDTO dto = new StatsDTO();

        // 1. Ricavi totali
        double ricavi = ordini.stream()
                .map(Ordine::getCostoTotale)
                .filter(Objects::nonNull)
                .reduce(0.0, Double::sum);

        dto.setRicaviTotali(ricavi);

        // 2. Costi totali (es: somma prezzi base pizze)
        double costi = ordini.stream()
                .flatMap(o -> o.getPizze().stream())
                .map(Pizza::getPrezzoBase)
                .reduce(0.0, Double::sum);

        dto.setCostiTotali(costi);

        // 3. Numero ordini
        dto.setNumeroOrdini((long) ordini.size());

        // 4. Numero pizze
        long numeroPizze = ordini.stream()
                .flatMap(o -> o.getPizze().stream())
                .count();

        dto.setNumeroPizze(numeroPizze);

        // 5. Clienti virtuosi (>100€)
        List<ClienteDTO> clientiVirtuosi = ordini.stream()
                .filter(o -> o.getCostoTotale() != null && o.getCostoTotale() > 100)
                .map(Ordine::getCliente)
                .distinct()
                .map(ClienteDTO::buildClienteDTOFromModel)
                .toList();

        dto.setClientiVirtuosi(clientiVirtuosi);

        return dto;
    }
}
