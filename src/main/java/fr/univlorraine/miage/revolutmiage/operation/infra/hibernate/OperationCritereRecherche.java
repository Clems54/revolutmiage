package fr.univlorraine.miage.revolutmiage.operation.infra.hibernate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationCritereRecherche {
    private String key;
    private String operation;
    private Object value;
}
