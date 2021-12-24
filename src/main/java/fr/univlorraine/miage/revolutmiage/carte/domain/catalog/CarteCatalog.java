package fr.univlorraine.miage.revolutmiage.carte.domain.catalog;

import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;

import java.util.Optional;

public interface CarteCatalog {
    Carte save(Carte carte);

    Optional<Carte> findByNumeroCarte(String numeroCarte);

    void deleteById(String numeroCarte);
}
