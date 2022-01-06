package fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte;

import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.CompteInput;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateCompteInput extends CompteInput {
    private boolean isCreation;
}
