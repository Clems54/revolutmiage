package fr.univlorraine.miage.revolutmiage.operation.infra.rest;

import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation.UpdateOperation;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation.UpdateOperationInput;
import fr.univlorraine.miage.revolutmiage.operation.infra.mapper.OperationMapper;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/operations")
@RolesAllowed("ROLE_USER")
public class OperationResource extends DefaultResource {
    private final OperationCatalog catalog;
    private final OperationMapper operationMapper;
    private final UpdateOperation updateOperation;

    @GetMapping("{id}")
    public ResponseEntity<?> getOperationById(@PathVariable final UUID id) {
        return ResponseEntity.of(catalog.findById(id).map(operationMapper::toDto));
    }

    @PostMapping
    public ResponseEntity<?> creerOperation(@RequestBody final UpdateOperationInput input) {
        input.setDateOperation(LocalDateTime.now()).setIdOperation(UUID.randomUUID());
        updateOperation.accept(input.setCreation(true));
        return ResponseEntity.created(
                linkTo(Utilisateur.class).slash(input.getIdOperation()).toUri()
        ).build();
    }
}
