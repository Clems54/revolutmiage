package fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCarteImpl implements UpdateCarte {
    private final UpdateCarteValidater validater;
    private final CarteCatalog catalog;
    private final CompteCatalog compteCatalog;

    @Override
    public void accept(final UpdateCarteInput input) {
        validater.validate(input);

        final Carte toSave = new Carte()
                .setNumeroCarte(input.getNumeroCarte())
                .setCode(input.getCode())
                .setCryptogramme(input.getCryptogramme())
                .setBloquee(input.isBloquee())
                .setSansContact(input.isSansContact())
                .setLocalisation(input.isLocalisation())
                .setVirtuelle(input.isVirtuelle())
                .setPlafond(input.getPlafond())
                .setCompte(compteCatalog.getByIban(input.getCompteIban()));

        catalog.save(toSave);
    }
}
