package pizzastore.repository.cliente;

import org.springframework.data.jpa.repository.Query;
import pizzastore.dto.ClienteDTOConNumeroOrdini;
import pizzastore.model.Cliente;

import java.util.List;

public interface CustomClienteRepository {
    List<Cliente> findByExample(Cliente example);

}
