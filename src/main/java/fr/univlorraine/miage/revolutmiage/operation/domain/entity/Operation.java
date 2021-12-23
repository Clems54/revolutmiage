package fr.univlorraine.miage.revolutmiage.operation.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Operation {
    @Id
    private UUID idOperation;
    private LocalDateTime dateOperation;
    private String libelle;
    private double montant;
    private double taux;
    private String ibanCompteCrediteur;
    private String ibanCompteDebiteur;
    private String categorie;
    private String pays;
}
