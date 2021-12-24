package fr.univlorraine.miage.revolutmiage.operation.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(readOnly = false)
    public Operation save(final Operation operation) {
        return repository.save(operation);
    }

    @Override
    public List<Operation> findAll(final Specification<Operation> specs) {
        return repository.findAll(specs);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(final UUID id) {
        repository.deleteById(id);
    }
}
