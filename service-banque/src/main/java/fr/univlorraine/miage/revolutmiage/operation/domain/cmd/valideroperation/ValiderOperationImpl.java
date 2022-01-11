package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.valideroperation;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation.UpdateOperation;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation.UpdateOperationInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValiderOperationImpl implements ValiderOperation {
    private final ValiderOperationValidater validater;
    private final UpdateOperation updateOperation;
    private final CarteCatalog carteCatalog;

    @Override
    public void accept(final ValiderOperationInput input) {
        validater.validate(input);

        final UpdateOperationInput toSave = new UpdateOperationInput();
        toSave.setCreation(true).setIdOperation(UUID.randomUUID())
                .setDateOperation(LocalDateTime.now())
                .setCarte(input.getNumeroCarte())
                .setPays(input.getPays())
                .setCategorie(input.getCategorie())
                .setLibelle(input.getLibelle())
                .setMontant(input.getMontant())
                .setIbanCompteCrediteur(input.getIbanCommerce())
                .setIbanCompteDebiteur(carteCatalog.findByNumeroCarte(input.getNumeroCarte()).get().getCompteIban())
                .setSansContact(input.isSansContact());

        updateOperation.accept(toSave);
    }
}
