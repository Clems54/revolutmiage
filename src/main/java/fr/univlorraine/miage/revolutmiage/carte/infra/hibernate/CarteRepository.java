package fr.univlorraine.miage.revolutmiage.carte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarteRepository extends CrudRepository<Carte, String> {
    Optional<Carte> findByNumeroCarte(String numeroCarte);

    void deleteByNumeroCarte(String numeroCarte);

    @Query("SELECT carte FROM Carte carte INNER JOIN Compte compte ON carte.compteIban = compte.iban " +
            "WHERE compte.utilisateur.numeroPasseport = :currentUsername AND carte.numeroCarte = :numeroCarte")
    Optional<Carte> findByNumeroCarteAndUsername(@Param("numeroCarte") String numeroCarte, @Param("currentUsername") String currentUsername);
}
