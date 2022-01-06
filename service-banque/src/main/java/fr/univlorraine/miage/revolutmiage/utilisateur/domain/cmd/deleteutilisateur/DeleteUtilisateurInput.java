package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.deleteutilisateur;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(chain = true)
public class DeleteUtilisateurInput {
    @NotBlank
    @Pattern(regexp = "^[0-9]{2}[a-zA-Z]{2}[0-9]{5}$")
    private String numeroPasseport;
}
