package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.updateutilisateur;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UpdateUtilisateurValidater extends DefaultValidater<UpdateUtilisateurInput> {
    private final UtilisateurCatalog catalog;

    public UpdateUtilisateurValidater(final Validator validator, final UtilisateurCatalog catalog) {
        super("utilisateur", validator);
        this.catalog = catalog;
    }

    @Override
    protected Map<String, String> customValidate(final UpdateUtilisateurInput input) {
        final Map<String, String> problems = new HashMap<>();

        final Optional<Utilisateur> optionalUtilisateur = catalog.findByNumeroPasseport(input.getNumeroPasseport());
        if (optionalUtilisateur.isPresent() && input.isCreation()) {
            problems.put(key("numeropasseport", "exist"), "Ce numéro de passeport est déjà enregistré");
        } else if (!input.isCreation() && optionalUtilisateur.isEmpty()) {
            problems.put(key("numeropasseport", "notfound"), "L'utilisateur n'existe pas");
        }

        return problems;
    }
}

