package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UtilisateurInput {
    @NotBlank()
    @Length(min = 1, max = 20)
    private String nom;

    @NotBlank()
    @Length(min = 1, max = 20)
    private String prenom;

    @NotBlank()
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String dateDeNaissance;

    @NotBlank()
    @Length(min = 1, max = 20)
    private String pays;

    @NotBlank()
    @Pattern(regexp = "^[0-9]{2}[a-zA-Z]{2}[0-9]{5}$")
    private String numeroPasseport;

    @NotBlank()
    @Pattern(regexp = "^\\+[1-9]{1}[0-9]{3,14}$")
    private String numeroTelephone;
}
