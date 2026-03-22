package pizzastore.service;

import pizzastore.model.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> listAllElements();

    public Cliente caricaSingoloElemento(Long id);

    public Cliente caricaSingoloElementoEager(Long id);

    public void aggiorna(Cliente clienteInstance);

    public void inserisciNuovo(Cliente clienteInstance);

    void toggleAttivo(Long idCliente);

    public List<Cliente> findByExample(Cliente example);

}
