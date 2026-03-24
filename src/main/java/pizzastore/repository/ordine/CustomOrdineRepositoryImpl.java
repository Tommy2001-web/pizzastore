package pizzastore.repository.ordine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import pizzastore.model.Ordine;
import pizzastore.model.Pizza;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ordine> findByExample(Ordine example) {

        StringBuilder queryBuilder = new StringBuilder("select o from Ordine o ");
        Map<String, Object> paramMap = new HashMap<>();
        List<String> whereClauses = new ArrayList<>();

        boolean hasPizzaFilter = example.getPizze() != null && !example.getPizze().isEmpty();

        if (hasPizzaFilter) {
            queryBuilder.append("left join o.pizze p ");
        }

        queryBuilder.append(" where o.id = o.id ");

        // CODICE
        if (StringUtils.isNotBlank(example.getCodice())) {
            whereClauses.add(" o.codice like :codice ");
            paramMap.put("codice", "%" + example.getCodice() + "%");
        }

        // CLIENTE
        if (example.getCliente() != null && example.getCliente().getId() != null) {
            whereClauses.add(" o.cliente.id = :clienteId ");
            paramMap.put("clienteId", example.getCliente().getId());
        }

        // PIZZE (filtro IN)
        if (hasPizzaFilter) {
            whereClauses.add(" p.id in :pizzaIds ");
            paramMap.put("pizzaIds",
                    example.getPizze().stream()
                            .map(Pizza::getId)
                            .toList()
            );
        }

        // CLOSED
        if (example.getClosed() != null) {
            whereClauses.add(" o.closed = :closed ");
            paramMap.put("closed", example.getClosed());
        }

        // COSTO TOTALE (esempio: filtra ordini >= 0 se impostato)
        if (example.getCostoTotale() != null) {
            whereClauses.add(" o.costoTotale >= :costoTotale ");
            paramMap.put("costoTotale", example.getCostoTotale());
        }

        // Costruzione WHERE
        if (!whereClauses.isEmpty()) {
            queryBuilder.append(" and ").append(String.join(" and ", whereClauses));
        }

        // Se filtro pizze → raggruppa e conta
        if (hasPizzaFilter) {
            queryBuilder.append(" group by o.id ");
            queryBuilder.append(" having count(distinct p.id) = :pizzaCount ");
            paramMap.put("pizzaCount", example.getPizze().size());
        }

        TypedQuery<Ordine> query = entityManager.createQuery(queryBuilder.toString(), Ordine.class);
        paramMap.forEach(query::setParameter);

        return query.getResultList();
    }

    @Override
    public List<Ordine> findByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine) {

        StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");
        Map<String, Object> paramMap = new HashMap<>();

        if (dataInizio != null) {
            queryBuilder.append(" and o.dataOrdine >= :dataInizio ");
            paramMap.put("dataInizio", dataInizio);
        }

        if (dataFine != null) {
            queryBuilder.append(" and o.dataOrdine <= :dataFine ");
            paramMap.put("dataFine", dataFine);
        }

        TypedQuery<Ordine> query = entityManager.createQuery(queryBuilder.toString(), Ordine.class);
        paramMap.forEach(query::setParameter);

        return query.getResultList();
    }


}
