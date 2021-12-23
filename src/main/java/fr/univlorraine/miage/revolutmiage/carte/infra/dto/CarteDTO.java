package fr.univlorraine.miage.revolutmiage.carte.infra.dto;

import fr.univlorraine.miage.revolutmiage.compte.infra.dto.CompteDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class CarteDTO {
    private String numeroCarte;
    private String code;
    private String cryptogramme;
    private boolean bloquee;
    private boolean localisation;
    private int plafond;
    private boolean sansContact;
    private boolean virtuelle;
    private String compteIban;
}
