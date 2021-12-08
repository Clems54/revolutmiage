package fr.univlorraine.miage.revolutmiage.operation.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationHibernateAdapter implements OperationCatalog {
    private final OperationRepository repository;
}
