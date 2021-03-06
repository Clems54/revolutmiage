package fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UpdateCompteValidater extends DefaultValidater<UpdateCompteInput> {
    private final CompteCatalog catalog;
    private final UtilisateurCatalog utilisateurCatalog;

    public UpdateCompteValidater(final Validator validator, final CompteCatalog catalog, final UtilisateurCatalog utilisateurCatalog) {
        super("compte", validator);
        this.catalog = catalog;
        this.utilisateurCatalog = utilisateurCatalog;
    }

    @Override
    protected Map<String, String> customValidate(final UpdateCompteInput input) {
        final Map<String, String> problems = new HashMap<>();
        final Optional<Compte> optionalCompte = catalog.findByIban(input.getIban());
        if (optionalCompte.isPresent() && input.isCreation()) {
            problems.put(key("iban", "exist"), "Ce numéro d'IBAN est déjà enregistré");
        } else if (!input.isCreation() && optionalCompte.isEmpty()) {
            problems.put(key("iban", "notfound"), "Le compte n'existe pas");
        }

        final Optional<Utilisateur> optionalUtilisateur = utilisateurCatalog.findByNumeroPasseport(input.getNumeroPasseportUtilisateur());
        if (optionalUtilisateur.isEmpty()) {
            problems.put(key("utilisateur", "notfound"), "L'utilisateur n'existe pas");
        }

        return problems;
    }
}
