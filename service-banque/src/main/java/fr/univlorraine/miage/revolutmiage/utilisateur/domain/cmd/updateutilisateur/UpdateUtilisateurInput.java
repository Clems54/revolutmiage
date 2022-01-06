package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.updateutilisateur;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.UtilisateurInput;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateUtilisateurInput extends UtilisateurInput {
    private boolean isCreation;
    @NotBlank
    @Length(min = 5, max = 50)
    private String password;
}
