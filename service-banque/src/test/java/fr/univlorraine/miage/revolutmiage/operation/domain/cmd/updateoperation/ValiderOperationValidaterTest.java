package fr.univlorraine.miage.revolutmiage.operation.domain.cmd.updateoperation;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.operation.domain.catalog.OperationCatalog;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import fr.univlorraine.miage.revolutmiage.utils.domain.cmd.InputValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ValiderOperationValidaterTest {
    public static final String VALID_LIBELLE = "Paiement nouvel an";
    public static final UUID VALID_ID_OPERATION = UUID.randomUUID();
    public static final LocalDateTime VALID_DATETIME = LocalDateTime.now();
    public static final String VALID_CATEGORIE = "Personne";
    public static final double VALID_MONTANT = 150.10;
    public static final String VALID_PAYS = "France";
    public static final String VALID_IBAN = "FR4217569000702756912794W52";
    public static final String VALID_IBAN2 = "FR4217569000702756912794W51";
    public static final String VALID_CARTE = "4837210495832750";

    public static final String INVALID_CATEGORIE = "Pe";
    public static final double INVALID_MONTANT = -150.10;
    public static final String INVALID_PAYS = "";
    public static final String INVALID_IBAN = "FR3";
    public static final String INVALID_IBAN2 = "FR4";
    public static final String INVALID_CARTE = "666";
    public static final int VALID_PLAFOND = 10000;

    @Autowired
    private Validator validator;
    @Mock
    private CompteCatalog compteCatalog;
    @Mock
    private OperationCatalog operationCatalog;
    @Mock
    private CarteCatalog carteCatalog;

    private UpdateOperationValidater subject;

    @BeforeEach
    void beforeEach() {
        subject = new UpdateOperationValidater(validator, operationCatalog, compteCatalog, carteCatalog);
    }

    @Test
    void testAllOk() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2)
                .setCarte(VALID_CARTE);
        final ArrayList<Carte> cartes = new ArrayList<>() {{
            add(new Carte().setNumeroCarte(VALID_CARTE).setPlafond(VALID_PLAFOND));
        }};

        // WHEN
        Mockito.when(compteCatalog.findByIban(Mockito.any())).thenReturn(
                Optional.of(new Compte().setCartes(cartes).setSolde(VALID_MONTANT + 50)));
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation()));
        Mockito.when(carteCatalog.findByNumeroCarte(Mockito.any())).thenReturn(
                Optional.of(new Carte().setNumeroCarte(VALID_CARTE).setPlafond(VALID_PLAFOND)));
        subject.validate(validOperation);
    }

    @Test
    void testMauvaiseCategorie() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(INVALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testMauvaisMontant() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(INVALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testMauvaisPays() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(INVALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testMauvaisIbanCrediteur() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(INVALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testMauvaisIbanDebiteur() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(INVALID_IBAN2);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testMauvaiseCarte() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2)
                .setCarte(INVALID_CARTE);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testCarteBloquee() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2)
                .setCarte(VALID_CARTE);
        final ArrayList<Carte> cartes = new ArrayList<>() {{
            add(new Carte().setNumeroCarte(VALID_CARTE));
        }};

        // WHEN
        Mockito.when(compteCatalog.findByIban(Mockito.any())).thenReturn(Optional.of(new Compte().setCartes(cartes)));
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation()));
        Mockito.when(carteCatalog.findByNumeroCarte(Mockito.any())).thenReturn(
                Optional.of(new Carte().setNumeroCarte(VALID_CARTE).setBloquee(true)));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testCarteSansContactImpossible() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2)
                .setCarte(VALID_CARTE);
        final ArrayList<Carte> cartes = new ArrayList<>() {{
            add(new Carte().setNumeroCarte(VALID_CARTE));
        }};

        // WHEN
        Mockito.when(compteCatalog.findByIban(Mockito.any())).thenReturn(Optional.of(new Compte().setCartes(cartes)));
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation().setSansContact(true)));
        Mockito.when(carteCatalog.findByNumeroCarte(Mockito.any())).thenReturn(
                Optional.of(new Carte().setNumeroCarte(VALID_CARTE).setSansContact(false)));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testCartePlafondDepasse() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2)
                .setCarte(VALID_CARTE);
        final ArrayList<Carte> cartes = new ArrayList<>() {{
            add(new Carte().setNumeroCarte(VALID_CARTE));
        }};

        // WHEN
        Mockito.when(compteCatalog.findByIban(Mockito.any())).thenReturn(Optional.of(new Compte().setCartes(cartes)));
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation().setMontant(15)));
        Mockito.when(carteCatalog.findByNumeroCarte(Mockito.any())).thenReturn(
                Optional.of(new Carte().setNumeroCarte(VALID_CARTE).setPlafond(10)));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testSoldeDebiteurTropFaible() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2)
                .setCarte(VALID_CARTE);
        final ArrayList<Carte> cartes = new ArrayList<>() {{
            add(new Carte().setNumeroCarte(VALID_CARTE));
        }};

        // WHEN
        Mockito.when(compteCatalog.findByIban(Mockito.any())).thenReturn(
                Optional.of(new Compte().setCartes(cartes).setSolde(VALID_MONTANT - 10)));
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation()));
        Mockito.when(carteCatalog.findByNumeroCarte(Mockito.any())).thenReturn(
                Optional.of(new Carte().setNumeroCarte(VALID_CARTE)));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testAllNull() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testCompteCrediteurNoExist() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2);

        // WHEN
        Mockito.when(compteCatalog.findByIban(validOperation.getIbanCompteCrediteur())).thenReturn(Optional.empty());
        Mockito.when(compteCatalog.findByIban(validOperation.getIbanCompteDebiteur())).thenReturn(Optional.of(new Compte()));
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation()));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testCompteDebiteurNoExist() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2);

        // WHEN
        Mockito.when(compteCatalog.findByIban(validOperation.getIbanCompteCrediteur())).thenReturn(Optional.of(new Compte()));
        Mockito.when(compteCatalog.findByIban(validOperation.getIbanCompteDebiteur())).thenReturn(Optional.empty());
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation()));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testCarteNoExist() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2)
                .setCarte(VALID_CARTE);

        // WHEN
        Mockito.when(compteCatalog.findByIban(validOperation.getIbanCompteCrediteur())).thenReturn(Optional.of(new Compte()));
        Mockito.when(compteCatalog.findByIban(validOperation.getIbanCompteDebiteur())).thenReturn(Optional.of(new Compte()));
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation()));
        Mockito.when(carteCatalog.findByNumeroCarte(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validOperation));
    }

    @Test
    void testCartePasDansCompte() {
        // GIVEN
        final UpdateOperationInput validOperation = new UpdateOperationInput();
        validOperation.setCreation(false)
                .setIdOperation(VALID_ID_OPERATION)
                .setDateOperation(VALID_DATETIME)
                .setCategorie(VALID_CATEGORIE)
                .setLibelle(VALID_LIBELLE)
                .setMontant(VALID_MONTANT)
                .setPays(VALID_PAYS)
                .setIbanCompteCrediteur(VALID_IBAN)
                .setIbanCompteDebiteur(VALID_IBAN2)
                .setCarte(VALID_CARTE);
        final ArrayList<Carte> cartes = new ArrayList<>();

        // WHEN
        Mockito.when(compteCatalog.findByIban(Mockito.any())).thenReturn(Optional.of(new Compte().setCartes(cartes)));
        Mockito.when(operationCatalog.findById(Mockito.any())).thenReturn(Optional.of(new Operation()));
        Mockito.when(carteCatalog.findByNumeroCarte(Mockito.any())).thenReturn(Optional.of(new Carte().setNumeroCarte(VALID_CARTE)));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validOperation));
    }
}
