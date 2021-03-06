package fr.univlorraine.miage.revolutmiage.compte.infra.rest;

import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte.UpdateCarteInput;
import fr.univlorraine.miage.revolutmiage.carte.infra.mapper.CarteMapper;
import fr.univlorraine.miage.revolutmiage.carte.infra.rest.CarteResource;
import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte.DeleteCompte;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte.DeleteCompteInput;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte.UpdateCompte;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte.UpdateCompteInput;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.compte.infra.dto.CompteDTO;
import fr.univlorraine.miage.revolutmiage.compte.infra.mapper.CompteMapper;
import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import fr.univlorraine.miage.revolutmiage.operation.infra.dto.OperationDTO;
import fr.univlorraine.miage.revolutmiage.operation.infra.hibernate.OperationCritereRecherche;
import fr.univlorraine.miage.revolutmiage.operation.infra.hibernate.OperationSpecification;
import fr.univlorraine.miage.revolutmiage.operation.infra.mapper.OperationMapper;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comptes")
@RolesAllowed("ROLE_USER")
public class CompteResource extends DefaultResource {
    private final CompteCatalog catalog;
    private final OperationCatalog operationCatalog;
    private final UpdateCompte updateCompte;
    private final DeleteCompte deleteCompte;
    private final CompteMapper compteMapper;
    private final CarteMapper carteMapper;
    private final CarteResource carteResource;
    private final OperationMapper operationMapper;

    @GetMapping("{iban}")
    public EntityModel<CompteDTO> getCompteById(@PathVariable final String iban) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        final EntityModel<CompteDTO> resource = EntityModel.of(compteMapper.toDto(optionalCompte.get()));

        resource.add(linkTo(methodOn(this.getClass()).getCompteSolde(optionalCompte.get().getIban())).withRel("compte-solde"));
        resource.add(linkTo(methodOn(this.getClass()).getCompteCartes(optionalCompte.get().getIban())).withRel("compte-cartes"));
        resource.add(linkTo(methodOn(this.getClass()).getCompteOperations(optionalCompte.get().getIban(), Optional.empty(), Optional.empty())).withRel("compte-operations"));

        return resource;
    }

    @GetMapping("{iban}/solde")
    public ResponseEntity<?> getCompteSolde(@PathVariable final String iban) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(optionalCompte.map(compte -> SimpleResponse.sendDouble("solde", compte.getSolde())));
    }

    @GetMapping("{iban}/cartes")
    public ResponseEntity<?> getCompteCartes(@PathVariable final String iban) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(optionalCompte.map(Compte::getCartes).map(carteMapper::toDtos));
    }

    @GetMapping("{iban}/cartes/{numeroCarte}")
    public ResponseEntity<?> getCompteCarte(@PathVariable final String iban, @PathVariable final String numeroCarte) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(optionalCompte
                .map(Compte::getCartes).get().stream()
                .filter(carte -> numeroCarte.equals(carte.getNumeroCarte())).findFirst().map(carteMapper::toDto));
    }

    @GetMapping("{iban}/operations")
    public ResponseEntity<List<OperationDTO>> getCompteOperations(@PathVariable final String iban, @RequestParam final Optional<String> categorie, @RequestParam final Optional<String> pays) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }

        final OperationSpecification specCategorie = categorie.map(str -> new OperationSpecification(new OperationCritereRecherche("categorie", "=", str))).orElse(null);
        final OperationSpecification specPays = pays.map(str -> new OperationSpecification(new OperationCritereRecherche("pays", "=", str))).orElse(null);
        final OperationSpecification specCrediteur = new OperationSpecification(new OperationCritereRecherche("ibanCompteCrediteur", "=", iban));
        final OperationSpecification specDebiteur = new OperationSpecification(new OperationCritereRecherche("ibanCompteDebiteur", "=", iban));
        final List<Operation> operations = operationCatalog.findAll(Specification.where(specCrediteur).or(specDebiteur).and(Specification.where(specCategorie).and(specPays)));

        return ResponseEntity.ok(operations.stream().map(operationMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> creerCompte(@RequestBody final UpdateCompteInput input) {
        updateCompte.accept(input.setCreation(true));
        return ResponseEntity.created(
                linkTo(Utilisateur.class).slash(input.getNumeroPasseportUtilisateur()).toUri()
        ).build();
    }

    @PostMapping("{iban}/cartes")
    public ResponseEntity<?> creerCompteCarte(@PathVariable final String iban, @RequestBody final UpdateCarteInput input) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }

        input.setCompteIban(iban);
        return carteResource.creerCarte(input);
    }

    @PutMapping("{iban}/cartes/{numeroCarte}")
    public ResponseEntity<?> modifierCompteCarte(@PathVariable final String iban, @PathVariable final String numeroCarte, @RequestBody final UpdateCarteInput input) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }

        input.setCompteIban(iban);
        return carteResource.modifierCarte(numeroCarte, input);
    }

    @DeleteMapping("{iban}")
    public ResponseEntity<?> supprimerCompte(@PathVariable final String iban) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }
        deleteCompte.accept(new DeleteCompteInput().setIban(iban));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{iban}/cartes/{numeroCarte}")
    public ResponseEntity<?> supprimerCompteCarte(@PathVariable final String iban, @PathVariable final String numeroCarte) {
        final Optional<Compte> optionalCompte = catalog.findByIban(iban);

        if (optionalCompte.isEmpty() || !optionalCompte.get().getUtilisateur().getNumeroPasseport().equals(currentUsername())) {
            return ResponseEntity.notFound().build();
        }

        return carteResource.supprimerCarte(numeroCarte);
    }
}
