package fr.univlorraine.miage.revolutmiage.compte.infra.dto;

import fr.univlorraine.miage.revolutmiage.utilisateur.infra.dto.UtilisateurDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class CompteDTO {
    private String iban;
    private double solde;
    private UtilisateurDTO utilisateur;
}
