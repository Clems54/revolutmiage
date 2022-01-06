package fr.univlorraine.miage.revolutmiage.operation.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OperationRepository extends CrudRepository<Operation, UUID>, JpaSpecificationExecutor<Operation> {
    List<Operation> findAllByIbanCompteCrediteurOrIbanCompteDebiteur(String ibanCrediteur, String ibanDebiteur);
}
