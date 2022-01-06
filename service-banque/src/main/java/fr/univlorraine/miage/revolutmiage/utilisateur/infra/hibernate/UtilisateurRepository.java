package fr.univlorraine.miage.revolutmiage.utilisateur.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, String> {
    Utilisateur getByNumeroPasseport(String numeroPassport);

    Optional<Utilisateur> findByNumeroPasseport(String numeroPasseport);

    void deleteByNumeroPasseport(String numeroPasseport);
}
