package fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte;

import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.CarteInput;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateCarteInput extends CarteInput {
    private boolean isCreation;
}
