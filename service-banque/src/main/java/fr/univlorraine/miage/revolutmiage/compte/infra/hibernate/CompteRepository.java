package fr.univlorraine.miage.revolutmiage.compte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompteRepository extends CrudRepository<Compte, String> {
    Optional<Compte> findByIban(String iban);

    void deleteByIban(String iban);

    Compte getByIban(String iban);
}
