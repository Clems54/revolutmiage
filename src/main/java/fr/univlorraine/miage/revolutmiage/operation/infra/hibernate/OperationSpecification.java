package fr.univlorraine.miage.revolutmiage.operation.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class OperationSpecification implements Specification<Operation> {

    private OperationCritereRecherche critereRecherche;

    @Override
    public Predicate toPredicate(final Root<Operation> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        if (critereRecherche.getOperation().equalsIgnoreCase("=")) {
            return builder.equal(builder.upper(root.get(critereRecherche.getKey())), critereRecherche.getValue().toString().toUpperCase());
        } else {
            return null;
        }
    }
}
