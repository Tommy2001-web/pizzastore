package pizzastore.repository.ordine;

import pizzastore.model.Ordine;

import java.util.List;

public interface CustomOrdineRepository {
    List<Ordine> findByExample(Ordine example);
}
