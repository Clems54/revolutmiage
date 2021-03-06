package fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(chain = true)
public class DeleteCompteInput {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$")
    private String iban;
}
