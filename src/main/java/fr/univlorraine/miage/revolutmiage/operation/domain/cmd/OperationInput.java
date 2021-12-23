package fr.univlorraine.miage.revolutmiage.operation.domain.cmd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OperationInput {
    @NotNull
    private UUID idOperation;
    @NotNull
    @PastOrPresent
    private LocalDateTime dateOperation;
    private String libelle;
    @Positive
    private double montant;
    private double taux;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$")
    private String ibanCompteCrediteur;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$")
    private String ibanCompteDebiteur;
    @NotBlank
    @Length(min = 4, max = 25)
    private String categorie;
    @NotBlank
    @Length(min = 1, max = 20)
    private String pays;
}
