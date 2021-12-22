package fr.univlorraine.miage.revolutmiage.carte.domain.cmd.updatecarte;

import fr.univlorraine.miage.revolutmiage.carte.domain.catalog.CarteCatalog;
import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
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
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UpdateCarteValidaterTest {
    public static final String VALID_NUMERO_CARTE = "3856146946105733";
    public static final String VALID_CODE = "5400";
    public static final String VALID_CRYPTOGRAMME = "666";
    public static final int VALID_PLAFOND = 1000;
    public static final String VALID_COMPTE_IBAN = "FR4217569000702756912794W52";

    public static final String INVALID_NUMERO_CARTE = "46946105733";
    public static final String INVALID_CODE = "54";
    public static final String INVALID_CRYPTOGRAMME = "6";
    public static final int INVALID_PLAFOND = -1000;
    public static final String INVALID_COMPTE_IBAN = "7569000702756912794W52";

    @Autowired
    private Validator validator;
    @Mock
    private CarteCatalog carteCatalog;
    @Mock
    private CompteCatalog compteCatalog;

    private UpdateCarteValidater subject;

    @BeforeEach
    void beforeEach() {
        subject = new UpdateCarteValidater(validator, carteCatalog, compteCatalog);
    }

    @Test
    void testAllOk() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false)
                .setNumeroCarte(VALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(VALID_CODE)
                .setCryptogramme(VALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(VALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(VALID_COMPTE_IBAN);


        // WHEN
        Mockito.when(carteCatalog.findByNumeroCarte(validCarte.getNumeroCarte())).thenReturn(Optional.of(new Carte()));
        Mockito.when(compteCatalog.findByIban(validCarte.getCompteIban())).thenReturn(Optional.of(new Compte()));
        subject.validate(validCarte);
    }

    @Test
    void testMauvaisNumeroCarte() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false)
                .setNumeroCarte(INVALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(VALID_CODE)
                .setCryptogramme(VALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(VALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(VALID_COMPTE_IBAN);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCarte));
    }

    @Test
    void testMauvaisCode() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false)
                .setNumeroCarte(VALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(INVALID_CODE)
                .setCryptogramme(VALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(VALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(VALID_COMPTE_IBAN);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCarte));
    }

    @Test
    void testMauvaisCrypto() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false)
                .setNumeroCarte(VALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(VALID_CODE)
                .setCryptogramme(INVALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(VALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(VALID_COMPTE_IBAN);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCarte));
    }

    @Test
    void testMauvaisPlafond() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false)
                .setNumeroCarte(VALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(VALID_CODE)
                .setCryptogramme(VALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(INVALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(VALID_COMPTE_IBAN);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCarte));
    }

    @Test
    void testMauvaisIban() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false)
                .setNumeroCarte(VALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(VALID_CODE)
                .setCryptogramme(VALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(VALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(INVALID_COMPTE_IBAN);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCarte));
    }

    @Test
    void testAllNull() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCarte));
    }

    @Test
    void testCarteNoExist() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false)
                .setNumeroCarte(VALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(VALID_CODE)
                .setCryptogramme(VALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(VALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(VALID_COMPTE_IBAN);


        // WHEN
        Mockito.when(carteCatalog.findByNumeroCarte(validCarte.getNumeroCarte())).thenReturn(Optional.empty());
        Mockito.when(compteCatalog.findByIban(validCarte.getCompteIban())).thenReturn(Optional.of(new Compte()));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validCarte));
    }

    @Test
    void testCompteNoExist() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(false)
                .setNumeroCarte(VALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(VALID_CODE)
                .setCryptogramme(VALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(VALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(VALID_COMPTE_IBAN);


        // WHEN
        Mockito.when(carteCatalog.findByNumeroCarte(validCarte.getNumeroCarte())).thenReturn(Optional.of(new Carte()));
        Mockito.when(compteCatalog.findByIban(validCarte.getCompteIban())).thenReturn(Optional.empty());

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validCarte));
    }

    @Test
    void testCreateCarteExist() {
        // GIVEN
        final UpdateCarteInput validCarte = new UpdateCarteInput();
        validCarte.setCreation(true)
                .setNumeroCarte(VALID_NUMERO_CARTE)
                .setBloquee(false)
                .setCode(VALID_CODE)
                .setCryptogramme(VALID_CRYPTOGRAMME)
                .setLocalisation(false)
                .setSansContact(false)
                .setPlafond(VALID_PLAFOND)
                .setVirtuelle(false)
                .setCompteIban(VALID_COMPTE_IBAN);


        // WHEN
        Mockito.when(carteCatalog.findByNumeroCarte(validCarte.getNumeroCarte())).thenReturn(Optional.of(new Carte()));
        Mockito.when(compteCatalog.findByIban(validCarte.getCompteIban())).thenReturn(Optional.of(new Compte()));

        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validCarte));
    }
}
