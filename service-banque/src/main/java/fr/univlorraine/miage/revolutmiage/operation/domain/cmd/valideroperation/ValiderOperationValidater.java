package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.valideroperation;

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
public class ValiderOperationValidater extends DefaultValidater<ValiderOperationInput> {
    private final CompteCatalog compteCatalog;
    private final CarteCatalog carteCatalog;

    public ValiderOperationValidater(final Validator validator, final CompteCatalog compteCatalog, final CarteCatalog carteCatalog) {
        super("operation", validator);
        this.compteCatalog = compteCatalog;
        this.carteCatalog = carteCatalog;
    }

    @Override
    protected Map<String, String> customValidate(final ValiderOperationInput input) {
        final Map<String, String> problems = new HashMap<>();

        final Optional<Carte> optionalCarte = carteCatalog.findByNumeroCarte(input.getNumeroCarte());
        if (optionalCarte.isEmpty()) {
            problems.put(key("numerocarte", "notfound"), "Carte non identifiée");
        } else {
            final Carte carte = optionalCarte.get();
            if (!input.getCode().equals(carte.getCode()) && !input.isSansContact()) {
                problems.put(key("code", "incorrect"), "Le code de la carte est incorrect");
            }
        }

        final Optional<Compte> optionalCompte = compteCatalog.findByIban(input.getIbanCommerce());
        if (optionalCompte.isEmpty()) {
            problems.put(key("iban", "notfound"), "Compte commerce non existant");
        }

        return problems;
    }
}
