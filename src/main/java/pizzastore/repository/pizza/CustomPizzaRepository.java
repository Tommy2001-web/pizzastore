package pizzastore.repository.pizza;

import pizzastore.model.Cliente;
import pizzastore.model.Pizza;

import java.util.List;

public interface CustomPizzaRepository {
    List<Pizza> findByExample(Pizza example);
}
