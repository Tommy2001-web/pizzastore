package pizzastore.repository.cliente;

import pizzastore.model.Cliente;

import java.util.List;

public interface CustomClienteRepository {
    List<Cliente> findByExample(Cliente example);
}
