package fr.univlorraine.miage.revolutmiage.compte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompteHibernateAdapter implements CompteCatalog {
    private final CompteRepository repository;
}
