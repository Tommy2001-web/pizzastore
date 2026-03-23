package pizzastore.repository.ordine;

import pizzastore.model.Ordine;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomOrdineRepository {
    List<Ordine> findByExample(Ordine example);
    List<Ordine> findByDataOrdineBetween(LocalDateTime start, LocalDateTime end);
}
