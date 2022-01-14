package fr.univlorraine.miage.revolutmiage.compte.domain.cmd.updatecompte;

import fr.univlorraine.miage.revolutmiage.compte.domain.catalog.CompteCatalog;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.catalog.UtilisateurCatalog;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
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
class UpdateCompteValidaterTest {
    public static final String VALID_COMPTE_IBAN = "FR4217569000702756912794W52";
    public static final String INVALID_COMPTE_IBAN = "7569000702756912794W52";
    private static final String VALID_PASSEPORT = "54CM81453";
    private static final String INVALID_PASSEPORT = "54DS0967";
    private static final String VALID_PAYS  = "FRANCE";
    private static final String INVALID_PAYS  = "FRANCEFRANCEFRANCEFRANCEFRANCEFRANCEFRANCEFRANCEFRANCEFRANCEFRANCEFRANCE";

    @Autowired
    private Validator validator;
    @Mock
    private CompteCatalog compteCatalog;
    @Mock
    private UtilisateurCatalog utilisateurCatalog;

    private UpdateCompteValidater subject;

    @BeforeEach
    void beforeEach() {
        subject = new UpdateCompteValidater(validator, compteCatalog, utilisateurCatalog);
    }

    @Test
    void testAllOk() {
        // GIVEN
        final UpdateCompteInput validCompte = new UpdateCompteInput();
        validCompte
                .setCreation(false)
                .setIban(VALID_COMPTE_IBAN)
                .setNumeroPasseportUtilisateur(VALID_PASSEPORT)
                .setPays(VALID_PAYS);

        // WHEN
        Mockito.when(compteCatalog.findByIban(validCompte.getIban())).thenReturn(Optional.of(new Compte()));
        Mockito.when(utilisateurCatalog.findByNumeroPasseport(validCompte.getNumeroPasseportUtilisateur())).thenReturn(Optional.of(new Utilisateur()));
        subject.validate(validCompte);
    }

    @Test
    void testMauvaisIban() {
        // GIVEN
        final UpdateCompteInput validCompte = new UpdateCompteInput();
        validCompte
                .setCreation(false)
                .setIban(INVALID_COMPTE_IBAN)
                .setNumeroPasseportUtilisateur(VALID_PASSEPORT)
                .setPays(VALID_PAYS);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCompte));
    }

    @Test
    void testMauvaisPasseport() {
        // GIVEN
        final UpdateCompteInput validCompte = new UpdateCompteInput();
        validCompte
                .setCreation(false)
                .setIban(VALID_COMPTE_IBAN)
                .setNumeroPasseportUtilisateur(INVALID_PASSEPORT)
                .setPays(VALID_PAYS);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCompte));
    }

    @Test
    void testMauvaisPays() {
        // GIVEN
        final UpdateCompteInput validCompte = new UpdateCompteInput();
        validCompte
                .setCreation(false)
                .setIban(VALID_COMPTE_IBAN)
                .setNumeroPasseportUtilisateur(VALID_PASSEPORT)
                .setPays(INVALID_PAYS);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCompte));
    }

    @Test
    void testAllNull() {
        // GIVEN
        final UpdateCompteInput validCompte = new UpdateCompteInput();
        validCompte
                .setCreation(false);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validCompte));
    }

    @Test
    void testCompteNoExist() {
        // GIVEN
        final UpdateCompteInput validCompte = new UpdateCompteInput();
        validCompte
                .setCreation(false)
                .setIban(VALID_COMPTE_IBAN)
                .setNumeroPasseportUtilisateur(VALID_PASSEPORT)
                .setPays(VALID_PAYS);

        // WHEN
        Mockito.when(compteCatalog.findByIban(validCompte.getIban())).thenReturn(Optional.empty());
        Mockito.when(utilisateurCatalog.findByNumeroPasseport(validCompte.getNumeroPasseportUtilisateur())).thenReturn(Optional.of(new Utilisateur()));
        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validCompte));
    }

    @Test
    void testUtilisateurNoExist() {
        // GIVEN
        final UpdateCompteInput validCompte = new UpdateCompteInput();
        validCompte
                .setCreation(false)
                .setIban(VALID_COMPTE_IBAN)
                .setNumeroPasseportUtilisateur(VALID_PASSEPORT)
                .setPays(VALID_PAYS);

        // WHEN
        Mockito.when(compteCatalog.findByIban(validCompte.getIban())).thenReturn(Optional.of(new Compte()));
        Mockito.when(utilisateurCatalog.findByNumeroPasseport(validCompte.getNumeroPasseportUtilisateur())).thenReturn(Optional.empty());
        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validCompte));
    }

    @Test
    void testCreateCompteExist() {
        // GIVEN
        final UpdateCompteInput validCompte = new UpdateCompteInput();
        validCompte
                .setCreation(true)
                .setIban(VALID_COMPTE_IBAN)
                .setNumeroPasseportUtilisateur(VALID_PASSEPORT)
                .setPays(VALID_PAYS);

        // WHEN
        Mockito.when(compteCatalog.findByIban(validCompte.getIban())).thenReturn(Optional.of(new Compte()));
        Mockito.when(utilisateurCatalog.findByNumeroPasseport(validCompte.getNumeroPasseportUtilisateur())).thenReturn(Optional.of(new Utilisateur()));
        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validCompte));
    }
}
