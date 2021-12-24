package fr.univlorraine.miage.revolutmiage.carte.infra.hibernate;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarteHibernateAdapter implements CarteCatalog {
    private final CarteRepository repository;

    @Override
    @Transactional(readOnly = false)
    public Carte save(final Carte carte) {
        return repository.save(carte);
    }

    @Override
    public Optional<Carte> findByNumeroCarte(final String numeroCarte) {
        return repository.findByNumeroCarte(numeroCarte);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(final String numeroCarte) {
        repository.deleteByNumeroCarte(numeroCarte);
    }
}
