package fr.univlorraine.miage.revolutmiage.carte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import org.springframework.data.repository.CrudRepository;

public interface CarteRepository extends CrudRepository<Carte, String> {
}
