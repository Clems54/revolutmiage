package fr.univlorraine.miage.revolutmiage.carte.infra.rest;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.deletecarte.DeleteCarte;
import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.deletecarte.DeleteCarteInput;
import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte.UpdateCarte;
import fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte.UpdateCarteInput;
import fr.univlorraine.miage.revolutmiage.carte.infra.mapper.CarteMapper;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("api/cartes")
@RequiredArgsConstructor
@RolesAllowed("ROLE_USER")
public class CarteResource extends DefaultResource {
    private final UpdateCarte updateCarte;
    private final DeleteCarte deleteCarte;
    private final CarteCatalog catalog;
    private final CarteMapper carteMapper;

    @GetMapping("{numeroCarte}")
    public ResponseEntity<?> getCarte(@PathVariable final String numeroCarte) {
        return ResponseEntity.of(catalog.findByNumeroCarteAndUsername(numeroCarte, currentUsername()).map(carteMapper::toDto));
    }

    @PostMapping
    public ResponseEntity<?> creerCarte(@RequestBody final UpdateCarteInput input) {
        updateCarte.accept(input.setCreation(true));
        return ResponseEntity.created(
                linkTo(Utilisateur.class).slash(input.getNumeroCarte()).toUri()
        ).build();
    }

    @PutMapping("{numeroCarte}")
    public ResponseEntity<?> modifierCarte(@PathVariable final String numeroCarte, @RequestBody final UpdateCarteInput input) {
        if (catalog.findByNumeroCarteAndUsername(numeroCarte, currentUsername()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        input.setNumeroCarte(numeroCarte);
        updateCarte.accept(input.setCreation(false));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{numeroCarte}")
    public ResponseEntity<?> supprimerCarte(@PathVariable final String numeroCarte) {
        if (catalog.findByNumeroCarteAndUsername(numeroCarte, currentUsername()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        deleteCarte.accept(new DeleteCarteInput().setNumeroCarte(numeroCarte));
        return ResponseEntity.noContent().build();
    }
}
