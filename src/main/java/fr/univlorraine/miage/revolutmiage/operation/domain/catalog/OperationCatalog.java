package fr.univlorraine.miage.revolutmiage.operation.domain.catalog;

import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;

import java.util.Optional;
import java.util.UUID;

public interface OperationCatalog {
    Optional<Operation> findById(UUID id);

    Operation save(Operation toSave);
}
