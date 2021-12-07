package fr.univlorraine.miage.revolutmiage.compte.dto;

import fr.univlorraine.miage.revolutmiage.utilisateur.dto.UtilisateurDTO;
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
    private UtilisateurDTO utilisateur;
}
