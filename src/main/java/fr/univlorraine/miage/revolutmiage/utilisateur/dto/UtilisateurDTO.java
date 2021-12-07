package fr.univlorraine.miage.revolutmiage.utilisateur.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class UtilisateurDTO {
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String pays;
    private String numeroPasseport;
    private String numeroTelephone;
    private String secret;
}
