package fr.univlorraine.miage.revolutmiage.taux.domain.cmd.calcultaux;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CalculTauxInput {
    @NotBlank
    @NotNull
    private String source;
    @NotBlank
    @NotNull
    private String destination;
    @NotNull
    private double quantite;
}
