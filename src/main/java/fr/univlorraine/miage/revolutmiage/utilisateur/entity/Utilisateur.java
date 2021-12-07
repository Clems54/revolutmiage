package fr.univlorraine.miage.revolutmiage.utilisateur.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Utilisateur {
    @Id
    private String numeroPasseport;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String pays;
    private String numeroTelephone;
    private String secret;
}
