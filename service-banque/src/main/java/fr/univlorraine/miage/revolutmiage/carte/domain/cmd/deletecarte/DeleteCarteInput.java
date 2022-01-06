package fr.univlorraine.miage.revolutmiage.carte.domain.cmd.deletecarte;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(chain = true)
public class DeleteCarteInput {
    @NotBlank
    @Pattern(regexp = "^[0-9]{16}$")
    private String numeroCarte;
}
