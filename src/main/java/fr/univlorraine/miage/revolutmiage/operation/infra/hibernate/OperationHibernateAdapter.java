package fr.univlorraine.miage.revolutmiage.operation.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationHibernateAdapter implements OperationCatalog {
    private final OperationRepository repository;

    @Override
    public Optional<Operation> findById(final UUID id) {
        return repository.findById(id);
    }

    @Override
    public Operation save(final Operation operation) {
        return repository.save(operation);
    }
}
