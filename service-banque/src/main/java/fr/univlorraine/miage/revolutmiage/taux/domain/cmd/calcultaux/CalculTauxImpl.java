package fr.univlorraine.miage.revolutmiage.taux.domain.cmd.calcultaux;

import fr.univlorraine.miage.revolutmiage.taux.domain.catalog.TauxCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculTauxImpl implements CalculTaux {
    private final CalculTauxValidater validater;
    private final TauxCatalog tauxCatalog;

    @Override
    public Double apply(final CalculTauxInput calculTauxInput) {
        validater.validate(calculTauxInput);

        final double tauxConversion;
        if(!calculTauxInput.getSource().equalsIgnoreCase(calculTauxInput.getDestination())) {
            tauxConversion = tauxCatalog.findBySourceAndDestination(calculTauxInput.getSource(), calculTauxInput.getDestination()).get().getTaux();
        } else {
            tauxConversion = 1;
        }

        return calculTauxInput.getQuantite() * tauxConversion;
    }
}
