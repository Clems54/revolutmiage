package fr.univlorraine.miage.revolutmiage.operation.hibernate;

import fr.univlorraine.miage.revolutmiage.operation.entity.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OperationRepository extends CrudRepository<Operation, UUID> {
}
