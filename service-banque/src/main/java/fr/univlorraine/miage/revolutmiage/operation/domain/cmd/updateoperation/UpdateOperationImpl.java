package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import fr.univlorraine.miage.revolutmiage.taux.domain.cmd.calcultaux.CalculTaux;
import fr.univlorraine.miage.revolutmiage.taux.domain.cmd.calcultaux.CalculTauxInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOperationImpl implements UpdateOperation {
    private final UpdateOperationValidater validater;
    private final OperationCatalog catalog;
    private final CarteCatalog carteCatalog;
    private final CompteCatalog compteCatalog;
    private final CalculTaux calculTaux;

    @Override
    public void accept(final UpdateOperationInput input) {
        validater.validate(input);

        final Operation toSave = new Operation()
                .setIdOperation(input.getIdOperation())
                .setDateOperation(input.getDateOperation())
                .setCategorie(input.getCategorie())
                .setLibelle(input.getLibelle())
                .setMontant(input.getMontant())
                .setPays(input.getPays())
                .setIbanCompteCrediteur(input.getIbanCompteCrediteur())
                .setIbanCompteDebiteur(input.getIbanCompteDebiteur());
        if (input.getCarte() != null && !input.getCarte().isEmpty()) {
            toSave.setCarte(carteCatalog.findByNumeroCarte(input.getCarte()).get());
        }

        mettreAJourSoldesComptes(input);

        catalog.save(toSave);
    }

    private void mettreAJourSoldesComptes(final UpdateOperationInput input) {
        final Compte compteCrediteur = compteCatalog.getByIban(input.getIbanCompteCrediteur());
        final Compte compteDebiteur = compteCatalog.getByIban(input.getIbanCompteDebiteur());

        final double taux = calculTaux.apply(new CalculTauxInput()
                .setSource(compteDebiteur.getUtilisateur().getPays().toUpperCase())
                .setDestination(compteCrediteur.getUtilisateur().getPays().toUpperCase())
                .setQuantite(input.getMontant())
        );

        compteCrediteur.setSolde(compteCrediteur.getSolde() + (input.getMontant() * taux));
        compteDebiteur.setSolde(compteDebiteur.getSolde() - input.getMontant());
        compteCatalog.save(compteCrediteur);
        compteCatalog.save(compteDebiteur);
    }
}
