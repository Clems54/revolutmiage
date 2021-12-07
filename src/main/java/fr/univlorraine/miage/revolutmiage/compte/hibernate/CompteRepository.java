package fr.univlorraine.miage.revolutmiage.compte.hibernate;

import fr.univlorraine.miage.revolutmiage.compte.entity.Compte;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends CrudRepository<Compte, String> {
}
