package fr.univlorraine.miage.revolutmiage.operation.infra.rest;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation.UpdateOperation;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation.UpdateOperationInput;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.valideroperation.ValiderOperation;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.valideroperation.ValiderOperationInput;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import fr.univlorraine.miage.revolutmiage.operation.infra.dto.OperationDTO;
import fr.univlorraine.miage.revolutmiage.operation.infra.mapper.OperationMapper;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/operations")
public class OperationResource extends DefaultResource {
    private final OperationCatalog catalog;
    private final OperationMapper operationMapper;
    private final UpdateOperation updateOperation;
    private final ValiderOperation validerOperation;
    private final CompteCatalog compteCatalog;

    @GetMapping("{id}")
    @RolesAllowed("ROLE_USER")
    public EntityModel<OperationDTO> getOperationById(@PathVariable final UUID id) {
        final Optional<Operation> operationOptional = catalog.findById(id);

        if (operationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        final EntityModel<OperationDTO> resource = EntityModel.of(operationMapper.toDto(operationOptional.get()));

        resource.add(linkTo(methodOn(this.getClass()).getAllCompteOperations(operationOptional.get().getIbanCompteCrediteur())).withRel("comptecrediteur-operations"));
        resource.add(linkTo(methodOn(this.getClass()).getAllCompteOperations(operationOptional.get().getIbanCompteDebiteur())).withRel("comptedebiteur-operations"));

        return resource;
    }

    @GetMapping("comptes/{iban}")
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<?> getAllCompteOperations(@PathVariable final String iban) {
        final Optional<Compte> optionalCompte = compteCatalog.findByIban(iban);
        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(catalog.findAllByIdCompte(iban).stream().map(operationMapper::toDto));
    }

    @PostMapping("externe")
    public ResponseEntity<?> creerOperationExterne(@RequestBody final ValiderOperationInput input) {
        validerOperation.accept(input);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<?> creerOperation(@RequestBody final UpdateOperationInput input) {
        input.setDateOperation(LocalDateTime.now()).setIdOperation(UUID.randomUUID());
        updateOperation.accept(input.setCreation(true));
        return ResponseEntity.created(
                linkTo(Utilisateur.class).slash(input.getIdOperation()).toUri()
        ).build();
    }
}
