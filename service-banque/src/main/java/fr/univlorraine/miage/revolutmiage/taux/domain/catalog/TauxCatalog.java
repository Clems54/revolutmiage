package fr.univlorraine.miage.revolutmiage.taux.domain.catalog;

import fr.univlorraine.miage.revolutmiage.taux.domain.entity.Taux;

import java.util.Optional;

public interface TauxCatalog {
    Optional<Taux> findBySourceAndDestination(String source, String destination);
}
