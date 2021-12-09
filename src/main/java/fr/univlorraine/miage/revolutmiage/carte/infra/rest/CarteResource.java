package fr.univlorraine.miage.revolutmiage.carte.infra.rest;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.deletecarte.DeleteCarte;
import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.deletecarte.DeleteCarteInput;
import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte.UpdateCarte;
import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte.UpdateCarteInput;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@Transactional(readOnly = true)
@RequestMapping("cartes")
@RequiredArgsConstructor
public class CarteResource extends DefaultResource {
    private final UpdateCarte updateCarte;
    private final DeleteCarte deleteCarte;
    private final CarteCatalog catalog;

    @PostMapping
    @Transactional(readOnly = false)
    public ResponseEntity<?> creerCarte(@RequestBody final UpdateCarteInput input) {
        updateCarte.accept(input.setCreation(true));
        return ResponseEntity.created(
                linkTo(Utilisateur.class).slash(input.getNumeroCarte()).toUri()
        ).build();
    }

    @PutMapping("{numeroCarte}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> modifierCarte(@PathVariable final String numeroCarte, @RequestBody final UpdateCarteInput input) {
        if (catalog.findByNumeroCarte(numeroCarte).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        input.setNumeroCarte(numeroCarte);
        updateCarte.accept(input.setCreation(false));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{numeroCarte}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> supprimerCarte(@PathVariable final String numeroCarte) {
        if (catalog.findByNumeroCarte(numeroCarte).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        deleteCarte.accept(new DeleteCarteInput().setNumeroCarte(numeroCarte));
        return ResponseEntity.noContent().build();
    }
}
