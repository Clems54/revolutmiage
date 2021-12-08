package fr.univlorraine.miage.revolutmiage.compte.domain.entity;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Compte {
    @Id
    private String iban;
    @ManyToOne
    private Utilisateur utilisateur;
}
