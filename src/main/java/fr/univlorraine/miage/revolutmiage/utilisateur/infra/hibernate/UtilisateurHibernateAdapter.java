package fr.univlorraine.miage.revolutmiage.utilisateur.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UtilisateurHibernateAdapter implements UtilisateurCatalog {
    private final UtilisateurRepository repository;

    @Override
    public Utilisateur getByNumeroPasseport(final String numeroPassport) {
        return repository.getByNumeroPasseport(numeroPassport);
    }

    @Override
    public Utilisateur save(final Utilisateur utilisateur) {
        return repository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> findByNumeroPasseport(final String numeroPasseport) {
        return repository.findByNumeroPasseport(numeroPasseport);
    }
}
