package fr.univlorraine.miage.revolutmiage.taux.domain.cmd.calcultaux;

import fr.univlorraine.miage.revolutmiage.taux.domain.catalog.TauxCatalog;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

@Service
public class CalculTauxValidater extends DefaultValidater<CalculTauxInput> {
    private final TauxCatalog catalog;

    public CalculTauxValidater(final Validator validator, final TauxCatalog catalog) {
        super("taux", validator);
        this.catalog = catalog;
    }

    @Override
    protected Map<String, String> customValidate(final CalculTauxInput calculTauxInput) {
        final Map<String, String> problems = new HashMap<>();

        if (calculTauxInput.getQuantite() <= 0) {
            problems.put(key("quantite", "zero"), "La quantité doit être supérieur à zéro");
        }
        if (!calculTauxInput.getSource().equalsIgnoreCase(calculTauxInput.getDestination())) {
            if (catalog.findBySourceAndDestination(calculTauxInput.getSource(), calculTauxInput.getDestination()).isEmpty()) {
                problems.put(key("pays", "notfound"), "Le taux de conversion entre ces deux pays n'existe pas");
            }
        }

        return problems;
    }
}
