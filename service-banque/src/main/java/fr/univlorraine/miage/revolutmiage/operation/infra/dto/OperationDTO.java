package fr.univlorraine.miage.revolutmiage.operation.infra.dto;

import fr.univlorraine.miage.revolutmiage.carte.infra.dto.CarteDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class OperationDTO {
    private String idOperation;
    private String dateOperation;
    private String libelle;
    private double montant;
    private double taux;
    private String ibanCompteCrediteur;
    private String ibanCompteDebiteur;
    private String categorie;
    private String pays;
    private CarteDTO carte;
    private boolean sansContact;
}
