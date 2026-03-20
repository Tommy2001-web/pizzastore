package pizzastore.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import pizzastore.model.Cliente;

public interface ClienteRepository extends
        JpaRepository<Cliente, Long>,
        PagingAndSortingRepository<Cliente, Long>,
        JpaSpecificationExecutor<Cliente> {

}
