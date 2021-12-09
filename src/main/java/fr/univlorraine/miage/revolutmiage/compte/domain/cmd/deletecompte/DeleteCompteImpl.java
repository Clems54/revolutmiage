package fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCompteImpl implements DeleteCompte {
    private final DeleteCompteValidater validater;
    private final CompteCatalog catalog;

    @Override
    public void accept(final DeleteCompteInput input) {
        validater.validate(input);

        catalog.delete(input.getIban());
    }
}
