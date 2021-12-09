package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.updateutilisateur;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UpdateUtilisateurImpl implements UpdateUtilisateur {
    private final UpdateUtilisateurValidater validater;
    private final UtilisateurCatalog catalog;

    @Override
    public void accept(final UpdateUtilisateurInput input) {
        validater.validate(input);

        final Utilisateur toSave = new Utilisateur()
                .setNumeroPasseport(input.getNumeroPasseport())
                .setNom(input.getNom())
                .setPrenom(input.getPrenom())
                .setNumeroTelephone(input.getNumeroTelephone())
                .setPays(input.getPays())
                .setDateDeNaissance(LocalDate.parse(input.getDateDeNaissance()));

        if (input.isCreation()) {
            toSave.setSecret(RandomString.make(20));
        }

        catalog.save(toSave);
    }
}
