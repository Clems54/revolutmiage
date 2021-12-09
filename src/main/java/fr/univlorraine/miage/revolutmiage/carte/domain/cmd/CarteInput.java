package fr.univlorraine.miage.revolutmiage.carte.domain.cmd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CarteInput {
    @NotBlank
    @Pattern(regexp = "^[0-9]{16}$")
    private String numeroCarte;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}$")
    private String code;

    @NotBlank
    @Pattern(regexp = "^[0-9]{3}$")
    private String cryptogramme;

    @NotNull
    private boolean bloquee;

    @NotNull
    private boolean localisation;

    @NotNull
    @Positive
    private int plafond;

    @NotNull
    private boolean sansContact;

    @NotNull
    private boolean virtuelle;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$")
    private String compteIban;
}
