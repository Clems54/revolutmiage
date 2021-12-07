package fr.univlorraine.miage.revolutmiage.compte.entity;

import fr.univlorraine.miage.revolutmiage.utilisateur.entity.Utilisateur;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Compte {
    private String iban;
    private Utilisateur utilisateur;
}
