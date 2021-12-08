package fr.univlorraine.miage.revolutmiage.compte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends CrudRepository<Compte, String> {
}
