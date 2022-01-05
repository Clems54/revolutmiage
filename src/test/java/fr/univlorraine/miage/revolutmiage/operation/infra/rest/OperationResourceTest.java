package fr.univlorraine.miage.revolutmiage.operation.infra.rest;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation.UpdateOperationInput;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@WithMockUser(username = "34RT57419", roles = "USER")
class OperationResourceTest {
    private final static UUID ID_OPERATION = UUID.randomUUID();

    private static final String VALID_IBAN_CREDITEUR = "FR6710096000307973825727M73";
    private static final String VALID_IBAN_DEBITEUR = "FR6710096000307973825727M72";
    private static final String VALID_PAYS = "Chine";
    private static final String VALID_CATEGORIE = "PERSONNE";
    private static final double VALID_MONTANT = 250.0;
    public static final int VALID_SOLDE_CREDITEUR = 1000;
    public static final int VALID_SOLDE_DEBITEUR = 2000;

    @Autowired
    OperationResource subject;
    @Autowired
    OperationCatalog operationCatalog;
    @Autowired
    CompteCatalog compteCatalog;

    @BeforeEach
    void beforeEach() {
        final Operation toSave = new Operation()
                .setMontant(VALID_MONTANT)
                .setDateOperation(LocalDateTime.now())
                .setIdOperation(ID_OPERATION)
                .setCategorie(VALID_CATEGORIE)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN_CREDITEUR)
                .setIbanCompteDebiteur(VALID_IBAN_DEBITEUR);
        operationCatalog.save(toSave);

        final Compte compteCrediteur = new Compte()
                .setIban(VALID_IBAN_CREDITEUR)
                .setSolde(VALID_SOLDE_CREDITEUR);
        final Compte compteDebiteur = new Compte()
                .setIban(VALID_IBAN_DEBITEUR)
                .setSolde(VALID_SOLDE_DEBITEUR);
        compteCatalog.save(compteCrediteur);
        compteCatalog.save(compteDebiteur);
    }

    @AfterEach
    void afterEach() {
        operationCatalog.deleteById(ID_OPERATION);
        compteCatalog.deleteById(VALID_IBAN_CREDITEUR);
        compteCatalog.deleteById(VALID_IBAN_DEBITEUR);
    }

    @Test
    void testSiCompteCrediteurBonSolde() {
        // GIVEN
        final UpdateOperationInput input = new UpdateOperationInput();
        input.setMontant(VALID_MONTANT)
                .setDateOperation(LocalDateTime.now())
                .setCategorie(VALID_CATEGORIE)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN_CREDITEUR)
                .setIbanCompteDebiteur(VALID_IBAN_DEBITEUR);

        // WHEN
        final ResponseEntity<?> responseEntity = subject.creerOperation(input);
        final String[] location = responseEntity.getHeaders().getFirst("Location").split("/");
        final Operation operation = operationCatalog.findById(UUID.fromString(location[location.length - 1])).get();
        final Compte compteCrediteur = compteCatalog.getByIban(operation.getIbanCompteCrediteur());

        // THEN
        Assertions.assertEquals(UUID.fromString(location[location.length - 1]), operation.getIdOperation());
        Assertions.assertEquals(VALID_SOLDE_CREDITEUR + VALID_MONTANT, compteCrediteur.getSolde());
    }

    @Test
    void testSiCompteDebiteurBonSolde() {
        // GIVEN
        final UpdateOperationInput input = new UpdateOperationInput();
        input.setMontant(VALID_MONTANT)
                .setDateOperation(LocalDateTime.now())
                .setCategorie(VALID_CATEGORIE)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN_CREDITEUR)
                .setIbanCompteDebiteur(VALID_IBAN_DEBITEUR);

        // WHEN
        final ResponseEntity<?> responseEntity = subject.creerOperation(input);
        final String[] location = responseEntity.getHeaders().getFirst("Location").split("/");
        final Operation operation = operationCatalog.findById(UUID.fromString(location[location.length - 1])).get();
        final Compte compteDebiteur = compteCatalog.getByIban(operation.getIbanCompteDebiteur());

        // THEN
        Assertions.assertEquals(UUID.fromString(location[location.length - 1]), operation.getIdOperation());
        Assertions.assertEquals(VALID_SOLDE_DEBITEUR - VALID_MONTANT, compteDebiteur.getSolde());
    }
}
