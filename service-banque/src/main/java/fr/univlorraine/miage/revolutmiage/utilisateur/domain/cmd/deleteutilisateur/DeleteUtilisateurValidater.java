package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.deleteutilisateur;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteUtilisateurValidater extends DefaultValidater<DeleteUtilisateurInput> {
    private final UtilisateurCatalog catalog;

    public DeleteUtilisateurValidater(final Validator validator, final UtilisateurCatalog catalog) {
        super("utilisateur", validator);
        this.catalog = catalog;
    }

    @Override
    protected Map<String, String> customValidate(final DeleteUtilisateurInput input) {
        final Map<String, String> problems = new HashMap<>();

        return problems;
    }
}
