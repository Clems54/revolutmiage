package fr.univlorraine.miage.revolutmiage.operation.entity;

import fr.univlorraine.miage.revolutmiage.compte.entity.Compte;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @ManyToOne
    private Compte compteCrediteur;
    private String categorie;
    private String pays;
}
