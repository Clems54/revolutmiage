package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.deleteutilisateur;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUtilisateurImpl implements DeleteUtilisateur {
    private final DeleteUtilisateurValidater validater;
    private final UtilisateurCatalog catalog;

    @Override
    public void accept(final DeleteUtilisateurInput input) {
        validater.validate(input);

        catalog.delete(input.getNumeroPasseport());
    }
}
