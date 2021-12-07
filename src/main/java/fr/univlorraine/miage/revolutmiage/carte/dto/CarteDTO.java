package fr.univlorraine.miage.revolutmiage.carte.dto;

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
    private boolean plafond;
    private boolean sansContact;
    private boolean virtuelle;
}
