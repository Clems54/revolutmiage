package fr.univlorraine.miage.revolutmiage.carte.entity;

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
@Accessors(chain = true)
public class Carte {
    @Id
    private String numeroCarte;
    private String code;
    private String cryptogramme;
    private boolean bloquee;
    private boolean localisation;
    private boolean plafond;
    private boolean sansContact;
    private boolean virtuelle;
}
