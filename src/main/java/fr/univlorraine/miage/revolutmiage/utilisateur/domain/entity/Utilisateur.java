package fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity;

import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

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
    @OneToMany
    private List<Compte> comptes;
}
