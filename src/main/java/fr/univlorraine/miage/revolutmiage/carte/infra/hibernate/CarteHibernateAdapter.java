package fr.univlorraine.miage.revolutmiage.carte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarteHibernateAdapter implements CarteCatalog {
    private final CarteRepository repository;
}
