package fr.univlorraine.miage.revolutmiage.compte.domain.cmd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CompteInput {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$")
    private String iban;

    @NotBlank
    @Pattern(regexp = "^[0-9]{2}[a-zA-Z]{2}[0-9]{5}$")
    private String numeroPasseportUtilisateur;
}
