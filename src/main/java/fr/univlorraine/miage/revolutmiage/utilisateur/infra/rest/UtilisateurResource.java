package fr.univlorraine.miage.revolutmiage.utilisateur.infra.rest;

import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.deleteutilisateur.DeleteUtilisateur;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.deleteutilisateur.DeleteUtilisateurInput;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.updateutilisateur.UpdateUtilisateur;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.updateutilisateur.UpdateUtilisateurInput;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utils.infra.rest.DefaultResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@Transactional(readOnly = true)
@RequestMapping("utilisateurs")
@RequiredArgsConstructor
public class UtilisateurResource extends DefaultResource {
    private final UpdateUtilisateur updateUtilisateur;
    private final DeleteUtilisateur deleteUtilisateur;
    private final UtilisateurCatalog catalog;

    @PostMapping
    @Transactional(readOnly = false)
    public ResponseEntity<?> creerUtilisateur(@RequestBody final UpdateUtilisateurInput input) {
        updateUtilisateur.accept(input.setCreation(true));
        return ResponseEntity.created(
                linkTo(Utilisateur.class).slash(input.getNumeroPasseport()).toUri()
        ).build();
    }

    @PutMapping("{numeroPasseport}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> modifierUtilisateur(@PathVariable final String numeroPasseport, @RequestBody final UpdateUtilisateurInput input) {
        if (catalog.findByNumeroPasseport(numeroPasseport).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        input.setNumeroPasseport(numeroPasseport);
        updateUtilisateur.accept(input.setCreation(false));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{numeroPasseport}")
    @Transactional(readOnly = false)
    public ResponseEntity<?> supprimerUtilisateur(@PathVariable final String numeroPasseport) {
        if (catalog.findByNumeroPasseport(numeroPasseport).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        deleteUtilisateur.accept(new DeleteUtilisateurInput().setNumeroPasseport(numeroPasseport));
        return ResponseEntity.noContent().build();
    }
}
