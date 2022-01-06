package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.valideroperation;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Accessors(chain = true)
public class ValiderOperationInput {
    @NotBlank
    @Pattern(regexp = "^[0-9]{16}$")
    private String numeroCarte;
    @NotBlank
    @Pattern(regexp = "^[0-9]{4}$")
    private String code;
    @NotNull
    private boolean sansContact;
    @NotBlank
    @Length(min = 4, max = 25)
    private String categorie;
    @NotBlank
    @Length(min = 1, max = 20)
    private String pays;
    private String libelle;
    @Positive
    private double montant;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$")
    private String ibanCommerce;
}
