package fr.univlorraine.miage.revolutmiage.taux.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.taux.domain.entity.Taux;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TauxRepository extends CrudRepository<Taux, String> {
    Optional<Taux> findBySourceAndDestination(String source, String destination);
}
