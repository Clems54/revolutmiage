package fr.univlorraine.miage.revolutmiage.utilisateur.hibernate;

import fr.univlorraine.miage.revolutmiage.utilisateur.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, String> {
}
