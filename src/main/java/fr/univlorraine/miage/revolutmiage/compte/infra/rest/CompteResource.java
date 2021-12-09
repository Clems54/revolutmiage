package fr.univlorraine.miage.revolutmiage.compte.infra.rest;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte.DeleteCompte;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.deletecompte.DeleteCompteInput;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte.UpdateCompte;
import fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte.UpdateCompteInput;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("comptes")
public class CompteResource extends DefaultResource {
    private final CompteCatalog catalog;
    private final UpdateCompte updateCompte;
    private final DeleteCompte deleteCompte;

    @PostMapping
    @Transactional(readOnly = false)
    public ResponseEntity<?> creerCompte(@RequestBody final UpdateCompteInput input) {
        updateCompte.accept(input.setCreation(true));
        return ResponseEntity.created(
                linkTo(Utilisateur.class).slash(input.getNumeroPasseport()).toUri()
        ).build();
    }

    @PutMapping("{iban}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> modifierCompte(@PathVariable final String iban, @RequestBody final UpdateCompteInput input) {
        if (catalog.findByIban(iban).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        input.setIban(iban);
        updateCompte.accept(input.setCreation(false));
        return ResponseEntity.noContent().build();
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
