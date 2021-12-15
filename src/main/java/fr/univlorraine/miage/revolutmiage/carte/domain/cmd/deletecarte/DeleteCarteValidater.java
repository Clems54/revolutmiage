package fr.univlorraine.miage.revolutmiage.carte.domain.cmd.deletecarte;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Map;

@Service
public class DeleteCarteValidater extends DefaultValidater<DeleteCarteInput> {
    private final CarteCatalog catalog;

    public DeleteCarteValidater(final Validator validator, final CarteCatalog catalog) {
        super("carte", validator);
        this.catalog = catalog;
    }

    @Override
    protected void customValidate(final Map<String, String> problems, final DeleteCarteInput input) {

    }
}
