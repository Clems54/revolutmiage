package fr.univlorraine.miage.revolutmiage.compte.infra.rest;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import fr.univlorraine.miage.revolutmiage.operation.infra.dto.OperationDTO;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@WithMockUser(username = "34RT57419", roles = "USER")
class CompteResourceTest {
    private final static UUID ID_OPERATION = UUID.randomUUID();
    private final static UUID ID_OPERATION2 = UUID.randomUUID();
    private final static UUID ID_OPERATION3 = UUID.randomUUID();
    private final static UUID ID_OPERATION4 = UUID.randomUUID();

    private static final String VALID_IBAN_CREDITEUR = "FR765";
    private static final String VALID_IBAN_DEBITEUR = "FR766";
    private static final String VALID_PAYS = "Chine";
    private static final String VALID_CATEGORIE = "PERSONNE";
    private static final String INVALID_IBAN_CREDITEUR = "FR55";
    private static final String INVALID_IBAN_DEBITEUR = "FR56";
    private static final String INVALID_PAYS = "France";
    private static final String INVALID_CATEGORIE = "QUELQUUN";
    public static final String PASSEPORT = "34RT57419";

    @Autowired
    CompteResource subject;
    @Autowired
    OperationCatalog operationCatalog;
    @Autowired
    CompteCatalog compteCatalog;
    @Autowired
    UtilisateurCatalog utilisateurCatalog;

    @BeforeEach
    void beforeEach() {
        final Operation toSave = new Operation()
                .setDateOperation(LocalDateTime.now())
                .setIdOperation(ID_OPERATION)
                .setCategorie(VALID_CATEGORIE)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN_CREDITEUR)
                .setIbanCompteDebiteur(VALID_IBAN_DEBITEUR);
        operationCatalog.save(toSave);

        final Operation toSave2 = new Operation()
                .setDateOperation(LocalDateTime.now())
                .setIdOperation(ID_OPERATION2)
                .setCategorie("COMMERCE")
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN_CREDITEUR)
                .setIbanCompteDebiteur(VALID_IBAN_DEBITEUR);
        operationCatalog.save(toSave2);

        final Operation toSave3 = new Operation()
                .setDateOperation(LocalDateTime.now())
                .setIdOperation(ID_OPERATION3)
                .setCategorie(VALID_CATEGORIE)
                .setPays("Italie")
                .setIbanCompteCrediteur(VALID_IBAN_CREDITEUR)
                .setIbanCompteDebiteur(VALID_IBAN_DEBITEUR);
        operationCatalog.save(toSave3);

        final Operation toSave4 = new Operation()
                .setDateOperation(LocalDateTime.now())
                .setIdOperation(ID_OPERATION4)
                .setCategorie(VALID_CATEGORIE)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur("FR1")
                .setIbanCompteDebiteur("FR2");
        operationCatalog.save(toSave4);

        final Utilisateur user = new Utilisateur().setNumeroPasseport(PASSEPORT);
        utilisateurCatalog.save(user);

        final Compte compteCrediteur = new Compte()
                .setIban(VALID_IBAN_CREDITEUR).setUtilisateur(user);
        final Compte compteDebiteur = new Compte()
                .setIban(VALID_IBAN_DEBITEUR).setUtilisateur(user);
        compteCatalog.save(compteCrediteur);
        compteCatalog.save(compteDebiteur);
    }

    @AfterEach
    void afterEach() {
        operationCatalog.deleteById(ID_OPERATION);
        operationCatalog.deleteById(ID_OPERATION2);
        operationCatalog.deleteById(ID_OPERATION3);
        operationCatalog.deleteById(ID_OPERATION4);
        compteCatalog.deleteById(VALID_IBAN_CREDITEUR);
        compteCatalog.deleteById(VALID_IBAN_DEBITEUR);
        utilisateurCatalog.deleteById(PASSEPORT);
    }

    @Test
    void testAllOkCompteCrediteur() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(VALID_IBAN_CREDITEUR, Optional.of(VALID_CATEGORIE), Optional.of(VALID_PAYS));

        // THEN
        Assertions.assertEquals(1, compteOperations.getBody().size());
    }

    @Test
    void testAllOkCompteDebiteur() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(VALID_IBAN_DEBITEUR, Optional.of(VALID_CATEGORIE), Optional.of(VALID_PAYS));

        // THEN
        Assertions.assertEquals(1, compteOperations.getBody().size());
    }

    @Test
    void testAllOkSansRecherche() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(VALID_IBAN_DEBITEUR, Optional.empty(), Optional.empty());

        // THEN
        Assertions.assertEquals(3, compteOperations.getBody().size());
    }

    @Test
    void testAllOkJusteRechercheCategorie() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(VALID_IBAN_DEBITEUR, Optional.of(VALID_CATEGORIE), Optional.empty());


        // THEN
        Assertions.assertEquals(2, compteOperations.getBody().size());
    }

    @Test
    void testAllOkJusteRecherchePays() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(VALID_IBAN_DEBITEUR, Optional.empty(), Optional.of(VALID_PAYS));

        // THEN
        Assertions.assertEquals(2, compteOperations.getBody().size());
    }

    @Test
    void testCompteQuiExistePas() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(INVALID_IBAN_CREDITEUR, Optional.empty(), Optional.empty());

        // THEN
        Assertions.assertEquals(HttpStatus.NOT_FOUND, compteOperations.getStatusCode());
    }

    @Test
    void testMauvaiseCategorieEtBonPays() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(VALID_IBAN_DEBITEUR, Optional.of(INVALID_CATEGORIE), Optional.of(VALID_PAYS));

        // THEN
        Assertions.assertEquals(0, compteOperations.getBody().size());
    }

    @Test
    void testMauvaisPaysEtBonneCategorie() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(VALID_IBAN_DEBITEUR, Optional.of(VALID_CATEGORIE), Optional.of(INVALID_PAYS));

        // THEN
        Assertions.assertEquals(0, compteOperations.getBody().size());
    }

    @Test
    void testMauvaiseCategorieEtMauvaisPays() {
        // WHEN
        final ResponseEntity<List<OperationDTO>> compteOperations = subject.getCompteOperations(VALID_IBAN_DEBITEUR, Optional.of(INVALID_CATEGORIE), Optional.of(INVALID_PAYS));

        // THEN
        Assertions.assertEquals(0, compteOperations.getBody().size());
    }
}
