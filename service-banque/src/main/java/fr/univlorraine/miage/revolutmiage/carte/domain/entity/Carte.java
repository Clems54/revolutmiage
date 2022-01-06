package fr.univlorraine.miage.revolutmiage.carte.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class Carte {
    @Id
    private String numeroCarte;
    private String code;
    private String cryptogramme;
    private boolean bloquee;
    private boolean localisation;
    private int plafond;
    private boolean sansContact;
    private boolean virtuelle;
    private String compteIban;
}
