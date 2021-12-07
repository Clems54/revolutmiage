package fr.univlorraine.miage.revolutmiage.operation.dto;

import fr.univlorraine.miage.revolutmiage.compte.dto.CompteDTO;
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
    private CompteDTO compteCrediteur;
    private String categorie;
    private String pays;
}
