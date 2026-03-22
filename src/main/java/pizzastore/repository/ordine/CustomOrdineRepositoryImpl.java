package pizzastore.repository.ordine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import pizzastore.model.Ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ordine> findByExample(Ordine example) {
        StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id");
        Map<String, Object> paramMap = new HashMap<>();
        List<String> whereClauses = new ArrayList<>();

        if (StringUtils.isNotBlank(example.getCodice())) {
            whereClauses.add(" o.codice like :codice ");
            paramMap.put("codice", "%" + example.getCodice() + "%");
        }

        if (example.getCliente() != null && example.getCliente().getId() != null) {
            whereClauses.add(" o.cliente.id = :clienteId ");
            paramMap.put("clienteId", example.getCliente().getId());
        }

        if (!whereClauses.isEmpty()) {
            queryBuilder.append(" and ").append(String.join(" and ", whereClauses));
        }

        TypedQuery<Ordine> query = entityManager.createQuery(queryBuilder.toString(), Ordine.class);
        paramMap.forEach(query::setParameter);

        return query.getResultList();
    }
}
