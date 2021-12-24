package fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;

import java.util.Optional;

public interface UtilisateurCatalog {
    Utilisateur getByNumeroPasseport(String numeroPassport);

    Utilisateur save(Utilisateur utilisateur);

    Optional<Utilisateur> findByNumeroPasseport(String numeroPasseport);

    void deleteById(String numeroPasseport);
}
