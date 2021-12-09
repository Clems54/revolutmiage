package fr.univlorraine.miage.revolutmiage.compte.domain.catalog;

import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;

import java.util.Optional;

public interface CompteCatalog {
    Compte save(Compte compte);

    Optional<Compte> findByIban(String iban);

    void delete(String iban);

    Compte getByIban(String iban);
}
