package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
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
    private final CarteCatalog carteCatalog;

    public UpdateOperationValidater(final Validator validator, final OperationCatalog catalog, final CompteCatalog compteCatalog, final CarteCatalog carteCatalog) {
        super("operation", validator);
        this.catalog = catalog;
        this.compteCatalog = compteCatalog;
        this.carteCatalog = carteCatalog;
    }

    @Override
    protected Map<String, String> customValidate(final UpdateOperationInput input) {
        final Map<String, String> problems = new HashMap<>();

        final Optional<Operation> optionalOperation = catalog.findById(input.getIdOperation());
        final Optional<Compte> optionalCompteCrediteur = compteCatalog.findByIban(input.getIbanCompteCrediteur());
        final Optional<Compte> optionalCompteDebiteur = compteCatalog.findByIban(input.getIbanCompteDebiteur());

        if (optionalOperation.isPresent() && input.isCreation()) {
            problems.put(key("operation", "exist"), "L'opération est déjà enregistré");
        } else if (!input.isCreation() && optionalOperation.isEmpty()) {
            problems.put(key("operation", "notfound"), "L'opération n'existe pas");
        }

        if(optionalCompteDebiteur.isPresent() && input.getMontant() > optionalCompteDebiteur.get().getSolde()){
            problems.put(key("operation", "nomoney"), "Le compte débiteur n'a pas le solde suffisant");
        }

        checkCompteExist(problems, optionalCompteCrediteur, "comptecrediteur");
        checkCompteExist(problems, optionalCompteDebiteur, "comptedebiteur");

        checkCarte(input, problems, optionalCompteDebiteur, optionalOperation);

        return problems;
    }

    private void checkCarte(final UpdateOperationInput input, final Map<String, String> problems, final Optional<Compte> optionalCompteDebiteur, final Optional<Operation> optionalOperation) {
        if (input.getCarte() != null && !input.getCarte().isEmpty()) {
            final Optional<Carte> optionalCarte = carteCatalog.findByNumeroCarte(input.getCarte());
            if (optionalCarte.isEmpty()) {
                problems.put(key("carte", "notfound"), "La carte n'existe pas");
            } else {
                final Carte carte = optionalCarte.get();
                if (carte.isBloquee()) {
                    problems.put(key("carte", "bloquee"), "La carte est bloquée");
                }

                if (problems.isEmpty()) {
                    if (!optionalCompteDebiteur.get().getCartes().contains(carte)) {
                        // Mettre la même erreur permet de ne pas divulger l'existance de cette carte
                        problems.put(key("carte", "notfound"), "La carte n'existe pas");
                    }

                    final Operation operation = optionalOperation.get();
                    if (operation.isSansContact() && !carte.isSansContact()) {
                        problems.put(key("carte", "sanscontact"), "La carte ne permet pas le sans contact");
                    }
                    if (operation.getMontant() > carte.getPlafond()) {
                        problems.put(key("carte", "plafond"), "Le montant de l'opération dépasse le plafond de la carte");
                    }
                }
            }
        }

    }

    private void checkCompteExist(final Map<String, String> problems, final Optional<Compte> optionalCompte, final String attribut) {
        if (optionalCompte.isEmpty()) {
            problems.put(key(attribut, "notfound"), "Le compte n'existe pas");
        }
    }
}
