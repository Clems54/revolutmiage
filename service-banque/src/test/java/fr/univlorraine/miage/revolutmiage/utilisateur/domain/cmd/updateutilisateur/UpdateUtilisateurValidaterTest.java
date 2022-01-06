package fr.univlorraine.miage.revolutmiage.utilisateur.domain.cmd.updateutilisateur;

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
class UpdateUtilisateurValidaterTest {
    private static final String VALID_PASSEPORT = "54CM81453";
    private static final String VALID_NOM = "Nom";
    private static final String VALID_PRENOM = "Prénom";
    private static final String VALID_PAYS = "France";
    private static final String VALID_DATE_DE_NAISSANCE = "1990-12-25";
    private static final String VALID_NUMERO_TELEPHONE = "+33606060606";

    private static final String INVALID_PASSEPORT = "545681453";
    private static final String INVALID_NOM = "NomNomNomNomNomNomNomNomNomNomNomNomNomNomNomNom";
    private static final String INVALID_PRENOM = "PrénomPrénomPrénomPrénomPrénomPrénomPrénomPrénomPrénomPrénomPrénom";
    private static final String INVALID_PAYS = "";
    private static final String INVALID_DATE_DE_NAISSANCE = "12-25-1990";
    private static final String INVALID_NUMERO_TELEPHONE = "+3360609789798789888998760606";
    public static final String INVALID_PASSWORD = "pas";
    public static final String VALID_PASSWORD = "password";

    @Autowired
    private Validator validator;
    @Mock
    private UtilisateurCatalog utilisateurCatalog;

    private UpdateUtilisateurValidater subject;

    @BeforeEach
    void beforeEach() {
        subject = new UpdateUtilisateurValidater(validator, utilisateurCatalog);
    }

    @Test
    void testAllOk() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Mockito.when(utilisateurCatalog.findByNumeroPasseport(validUtilisateur.getNumeroPasseport())).thenReturn(Optional.of(new Utilisateur()));
        subject.validate(validUtilisateur);
    }

    @Test
    void testMauvaisPassword() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(INVALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testMauvaisPasseport() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(INVALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testMauvaisNom() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(INVALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testMauvaisPrenom() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(INVALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testMauvaisDateNaissance() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(INVALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testMauvaisPays() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(INVALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testMauvaisNumeroTelephone() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(INVALID_NUMERO_TELEPHONE);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testUtilisateurNoExist() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Mockito.when(utilisateurCatalog.findByNumeroPasseport(validUtilisateur.getNumeroPasseport())).thenReturn(Optional.empty());
        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testCreateUtilisateurExist() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(true);
        validUtilisateur.setPassword(VALID_PASSWORD)
                .setNumeroPasseport(VALID_PASSEPORT)
                .setNom(VALID_NOM)
                .setPrenom(VALID_PRENOM)
                .setDateDeNaissance(VALID_DATE_DE_NAISSANCE)
                .setPays(VALID_PAYS)
                .setNumeroTelephone(VALID_NUMERO_TELEPHONE);

        // WHEN
        Mockito.when(utilisateurCatalog.findByNumeroPasseport(validUtilisateur.getNumeroPasseport())).thenReturn(Optional.of(new Utilisateur()));
        Assertions.assertThrows(InputValidationException.class, () -> subject.validate(validUtilisateur));
    }

    @Test
    void testAllNull() {
        // GIVEN
        final UpdateUtilisateurInput validUtilisateur = new UpdateUtilisateurInput();
        validUtilisateur.setCreation(false);

        // WHEN
        Assertions.assertThrows(ConstraintViolationException.class, () -> subject.validate(validUtilisateur));
    }
}
