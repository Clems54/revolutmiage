package fr.univlorraine.miage.revolutmiage.compte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompteHibernateAdapter implements CompteCatalog {
    private final CompteRepository repository;

    @Override
    @Transactional(readOnly = false)
    public Compte save(final Compte compte) {
        return repository.save(compte);
    }

    @Override
    public Optional<Compte> findByIban(final String iban) {
        return repository.findByIban(iban);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(final String iban) {
        repository.deleteByIban(iban);
    }

    @Override
    public Compte getByIban(final String iban) {
        return repository.getByIban(iban);
    }
}
