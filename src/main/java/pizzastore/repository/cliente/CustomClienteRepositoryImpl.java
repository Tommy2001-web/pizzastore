package pizzastore.repository.cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import pizzastore.dto.ClienteDTOConNumeroOrdini;
import pizzastore.model.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomClienteRepositoryImpl implements CustomClienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cliente> findByExample(Cliente example) {

        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select c from Cliente c where c.id = c.id ");

        if (StringUtils.isNotEmpty(example.getNome())) {
            whereClauses.add(" c.nome  like :nome ");
            paramaterMap.put("nome", "%" + example.getNome() + "%");
        }
        if (StringUtils.isNotEmpty(example.getCognome())) {
            whereClauses.add(" c.cognome like :cognome ");
            paramaterMap.put("cognome", "%" + example.getCognome() + "%");
        }
        if (StringUtils.isNotEmpty(example.getIndirizzo())) {
            whereClauses.add(" c.indirizzo like :indirizzo ");
            paramaterMap.put("indirizzo", "%" + example.getIndirizzo() + "%");
        }

        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Cliente.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }

}

