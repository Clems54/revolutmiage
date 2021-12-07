package fr.univlorraine.miage.revolutmiage.utilisateur.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Utilisateur {
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String pays;
    private String numeroPasseport;
    private String numeroTelephone;
    private String secret;
}
