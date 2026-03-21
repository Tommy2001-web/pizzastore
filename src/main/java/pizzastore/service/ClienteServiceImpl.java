package pizzastore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzastore.model.Cliente;
import pizzastore.repository.cliente.ClienteRepository;

import java.util.List;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listAllElements() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente caricaSingoloElemento(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    public void aggiorna(Cliente clienteInstance) {

    }

    @Override
    public void inserisciNuovo(Cliente clienteInstance) {
        clienteInstance.setAttivo(true);
        clienteRepository.save(clienteInstance);
    }

    @Override
    public void disattiva(Long idCliente) {

    }

    @Override
    public List<Cliente> findByExample(Cliente example) {

        return clienteRepository.findByExample(example);
    }
}
