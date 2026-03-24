package pizzastore.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pizzastore.dto.ClienteDTOConNumeroOrdini;
import pizzastore.model.Cliente;

import java.util.List;

public interface ClienteRepository extends
        JpaRepository<Cliente, Long>,
        PagingAndSortingRepository<Cliente, Long>,
        JpaSpecificationExecutor<Cliente>,
        CustomClienteRepository {

    @Query("""
    SELECT new pizzastore.dto.ClienteDTOConNumeroOrdini(
        c.id,
        c.nome,
        c.cognome,
        c.indirizzo,
        c.attivo,
        COUNT(o)
    )
    FROM Cliente c
    LEFT JOIN c.ordini o
    GROUP BY c.id, c.nome, c.cognome, c.indirizzo, c.attivo
""")
    List<ClienteDTOConNumeroOrdini> findClientiConNumeroOrdini();
}
