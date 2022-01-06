package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation;

import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.OperationInput;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateOperationInput extends OperationInput {
    private boolean isCreation;
}
