package pizzastore.repository.pizza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import pizzastore.model.Pizza;

public interface PizzaRepository extends
        JpaRepository<Pizza, Long>,
        PagingAndSortingRepository<Pizza, Long>,
        JpaSpecificationExecutor<Pizza> {

}
