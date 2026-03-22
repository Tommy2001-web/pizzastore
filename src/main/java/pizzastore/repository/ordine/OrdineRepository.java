package pizzastore.repository.ordine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import pizzastore.model.Ordine;

public interface OrdineRepository extends
        JpaRepository<Ordine, Long>,
        PagingAndSortingRepository<Ordine, Long>,
        JpaSpecificationExecutor<Ordine>,
        CustomOrdineRepository {
}
