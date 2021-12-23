package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation;

import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOperationImpl implements UpdateOperation {
    private final UpdateOperationValidater validater;
    private final OperationCatalog catalog;

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
                .setTaux(input.getTaux())
                .setIbanCompteCrediteur(input.getIbanCompteCrediteur())
                .setIbanCompteDebiteur(input.getIbanCompteDebiteur());

        catalog.save(toSave);
    }
}
