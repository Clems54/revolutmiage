package fr.univlorraine.miage.revolutmiage.compte.infra.dto;

import fr.univlorraine.miage.revolutmiage.carte.infra.dto.CarteDTO;
import fr.univlorraine.miage.revolutmiage.utilisateur.infra.dto.UtilisateurDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class CompteDTO {
    private String iban;
    private double solde;
    private List<CarteDTO> cartes;
    private UtilisateurDTO utilisateur;
    private String pays;
}
