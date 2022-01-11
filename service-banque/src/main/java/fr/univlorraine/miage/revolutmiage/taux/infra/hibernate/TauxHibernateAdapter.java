package fr.univlorraine.miage.revolutmiage.taux.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.carte.infra.hibernate.CarteRepository;
import fr.univlorraine.miage.revolutmiage.taux.domain.catalog.TauxCatalog;
import fr.univlorraine.miage.revolutmiage.taux.domain.entity.Taux;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TauxHibernateAdapter implements TauxCatalog {
    private final TauxRepository repository;

    @Override
    public Optional<Taux> findBySourceAndDestination(final String source, final String destination) {
        return repository.findBySourceAndDestination(source, destination);
    }
}
