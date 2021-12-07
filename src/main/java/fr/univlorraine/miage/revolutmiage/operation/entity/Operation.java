package fr.univlorraine.miage.revolutmiage.operation.entity;

import fr.univlorraine.miage.revolutmiage.compte.entity.Compte;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Operation {
    private LocalDateTime dateOperation;
    private String libellle;
    private double montant;
    private double taux;
    private Compte compteCrediteur;
    private String categorie;
    private String pays;
}
