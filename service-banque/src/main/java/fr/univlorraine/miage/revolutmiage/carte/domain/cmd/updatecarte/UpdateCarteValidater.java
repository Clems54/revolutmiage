package fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UpdateCarteValidater extends DefaultValidater<UpdateCarteInput> {
    private final CarteCatalog catalog;
    private final CompteCatalog compteCatalog;

    public UpdateCarteValidater(final Validator validator, final CarteCatalog catalog, final CompteCatalog compteCatalog) {
        super("carte", validator);
        this.catalog = catalog;
        this.compteCatalog = compteCatalog;
    }

    @Override
    protected Map<String, String> customValidate(final UpdateCarteInput input) {
        final Map<String, String> problems = new HashMap<>();
        final Optional<Carte> optionalCarte = catalog.findByNumeroCarte(input.getNumeroCarte());
        if (optionalCarte.isPresent() && input.isCreation()) {
            problems.put(key("numerocarte", "exist"), "Ce numéro de carte est déjà enregistré");
        } else if (!input.isCreation() && optionalCarte.isEmpty()) {
            problems.put(key("numerocarte", "notfound"), "La carte n'existe pas");
        }

        final Optional<Compte> optionalCompte = compteCatalog.findByIban(input.getCompteIban());
        if (optionalCompte.isEmpty()) {
            problems.put(key("compte", "notfound"), "Le compte n'existe pas");
        }

        return problems;
    }
}
