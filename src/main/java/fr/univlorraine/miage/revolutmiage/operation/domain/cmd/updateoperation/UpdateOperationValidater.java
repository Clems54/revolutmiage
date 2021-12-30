package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UpdateOperationValidater extends DefaultValidater<UpdateOperationInput> {
    private final OperationCatalog catalog;
    private final CompteCatalog compteCatalog;

    public UpdateOperationValidater(final Validator validator, final OperationCatalog catalog, final CompteCatalog compteCatalog) {
        super("operation", validator);
        this.catalog = catalog;
        this.compteCatalog = compteCatalog;
    }

    @Override
    protected Map<String, String> customValidate(final UpdateOperationInput input) {
        final Map<String, String> problems = new HashMap<>();
        final Optional<Operation> optionalCarte = catalog.findById(input.getIdOperation());
        if (optionalCarte.isPresent() && input.isCreation()) {
            problems.put(key("operation", "exist"), "L'opération est déjà enregistré");
        } else if (!input.isCreation() && optionalCarte.isEmpty()) {
            problems.put(key("operation", "notfound"), "L'opération n'existe pas");
        }
        checkCompteExist(problems, input.getIbanCompteCrediteur(), "comptecrediteur");
        checkCompteExist(problems, input.getIbanCompteDebiteur(), "comptedebiteur");

        return problems;
    }

    private void checkCompteExist(final Map<String, String> problems, final String iban, final String attribut) {
        final Optional<Compte> optionalCompte = compteCatalog.findByIban(iban);
        if (optionalCompte.isEmpty()) {
            problems.put(key(attribut, "notfound"), "Le compte n'existe pas");
        }
    }


}
