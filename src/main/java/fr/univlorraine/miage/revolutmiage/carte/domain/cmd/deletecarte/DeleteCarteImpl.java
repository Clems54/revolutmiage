package fr.univlorraine.miage.revolutmiage.carte.domain.cmd.deletecarte;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCarteImpl implements DeleteCarte {
    private final DeleteCarteValidater validater;
    private final CarteCatalog catalog;

    @Override
    public void accept(final DeleteCarteInput input) {
        validater.validate(input);

        catalog.deleteById(input.getNumeroCarte());
    }
}
