package fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCompteImpl implements UpdateCompte {
    private final UpdateCompteValidater validater;
    private final CompteCatalog catalog;
    private final UtilisateurCatalog utilisateurCatalog;

    @Override
    public void accept(final UpdateCompteInput input) {
        validater.validate(input);

        final Compte toSave = new Compte()
                .setIban(input.getIban())
                .setUtilisateur(utilisateurCatalog.getByNumeroPasseport(input.getNumeroPasseport()));

        catalog.save(toSave);
    }
}
