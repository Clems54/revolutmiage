package fr.univlorraine.miage.revolutmiage.utilisateur.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UtilisateurHibernateAdapter implements UtilisateurCatalog {
    private final UtilisateurRepository repository;
}
