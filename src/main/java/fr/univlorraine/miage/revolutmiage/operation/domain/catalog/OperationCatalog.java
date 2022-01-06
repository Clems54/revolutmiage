package fr.univlorraine.miage.revolutmiage.operation.domain.catalog;

import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OperationCatalog {
    Optional<Operation> findById(UUID id);

    Operation save(Operation toSave);

    List<Operation> findAll(Specification<Operation> specs);

    void deleteById(UUID id);

    List<Operation> findAllByIdCompte(String iban);
}
