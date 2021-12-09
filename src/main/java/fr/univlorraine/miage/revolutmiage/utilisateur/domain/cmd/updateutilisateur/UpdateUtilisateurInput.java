package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.updateutilisateur;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.UtilisateurInput;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateUtilisateurInput extends UtilisateurInput {
    private boolean isCreation;
}
