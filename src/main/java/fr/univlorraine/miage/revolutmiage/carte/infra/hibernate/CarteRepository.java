package fr.univlorraine.miage.revolutmiage.carte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarteRepository extends CrudRepository<Carte, String> {
    Optional<Carte> findByNumeroCarte(String numeroCarte);

    void deleteByNumeroCarte(String numeroCarte);
}
