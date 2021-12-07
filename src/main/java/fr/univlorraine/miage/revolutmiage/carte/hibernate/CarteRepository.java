package fr.univlorraine.miage.revolutmiage.carte.hibernate;

import fr.univlorraine.miage.revolutmiage.carte.entity.Carte;
import org.springframework.data.repository.CrudRepository;

public interface CarteRepository extends CrudRepository<Carte, String> {
}
