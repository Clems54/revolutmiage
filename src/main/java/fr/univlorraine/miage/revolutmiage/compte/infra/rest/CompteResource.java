package fr.univlorraine.miage.revolutmiage.compte.infra.rest;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte.DeleteCompte;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte.DeleteCompteInput;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte.UpdateCompte;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte.UpdateCompteInput;
import fr.univlorraine.miage.revolutmiage.compte.infra.mapper.CompteMapper;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comptes")
@RolesAllowed("ROLE_USER")
public class CompteResource extends DefaultResource {
    private final CompteCatalog catalog;
    private final UpdateCompte updateCompte;
    private final DeleteCompte deleteCompte;
    private final CompteMapper compteMapper;

    @GetMapping("{iban}")
    public ResponseEntity<?> getCompteById(@PathVariable final String iban) {
        return ResponseEntity.of(catalog.findByIban(iban).map(compteMapper::toDto));
    }

    @GetMapping("{iban}/solde")
    public ResponseEntity<?> getCompteSolde(@PathVariable final String iban) {
        return ResponseEntity.of(catalog.findByIban(iban).map(compte -> SimpleResponse.sendDouble("solde", compte.getSolde())));
    }

    @GetMapping("{iban}/cartes")
    public ResponseEntity<?> getCompteCartes(@PathVariable final String iban) {
        return ResponseEntity.of(catalog.findByIban(iban).map(compte -> SimpleResponse.sendObjectList("cartes", compte.getCartes())));
    }

    @PostMapping
    @Transactional(readOnly = false)
    public ResponseEntity<?> creerCompte(@RequestBody final UpdateCompteInput input) {
        updateCompte.accept(input.setCreation(true));
        return ResponseEntity.created(
                linkTo(Utilisateur.class).slash(input.getNumeroPasseportUtilisateur()).toUri()
        ).build();
    }

    @DeleteMapping("{iban}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> supprimerCompte(@PathVariable final String iban) {
        if (catalog.findByIban(iban).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        deleteCompte.accept(new DeleteCompteInput().setIban(iban));
        return ResponseEntity.noContent().build();
    }
}
