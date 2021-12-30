package fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteCompteValidater extends DefaultValidater<DeleteCompteInput> {
    private final CompteCatalog catalog;

    public DeleteCompteValidater(final Validator validator, final CompteCatalog catalog) {
        super("compte", validator);
        this.catalog = catalog;
    }

    @Override
    protected Map<String, String> customValidate(final DeleteCompteInput input) {
        final Map<String, String> problems = new HashMap<>();

        return problems;
    }
}
