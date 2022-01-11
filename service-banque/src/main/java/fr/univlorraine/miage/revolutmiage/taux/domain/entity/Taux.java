package fr.univlorraine.miage.revolutmiage.taux.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Taux {
    @Id
    private String source;
    private String destination;
    private double taux;
}
