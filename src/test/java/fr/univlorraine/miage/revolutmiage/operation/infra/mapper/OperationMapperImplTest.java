package fr.univlorraine.miage.revolutmiage.operation.infra.mapper;

import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.carte.infra.dto.CarteDTO;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import fr.univlorraine.miage.revolutmiage.operation.infra.dto.OperationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class OperationMapperImplTest {
    public static final String IBAN = "FR761034328909432347";
    public static final String IBAN2 = "FR761034485623415647";
    public static final String LIBELLE = "Libelle";
    public static final String CATEGORIE = "Categorie";
    public static final LocalDateTime DATE_OPERATION = LocalDateTime.now();
    public static final UUID ID_OPERATION = UUID.randomUUID();
    public static final double MONTANT = 10.5;
    public static final String PAYS = "Pays";
    public static final int TAUX = 10;
    public static final Carte CARTE_OBJ = new Carte().setNumeroCarte("2901647509136723");
    public static final CarteDTO CARTE_DTO = new CarteDTO().setNumeroCarte("2901647509136723");

    @Autowired
    private OperationMapperImpl subject;

    @Test
    @DisplayName("Devrait retourner l'obj en DTO")
    void toDto() {
        // GIVEN
        final Operation obj = new Operation()
                .setLibelle(LIBELLE)
                .setCategorie(CATEGORIE)
                .setDateOperation(DATE_OPERATION)
                .setIdOperation(ID_OPERATION)
                .setMontant(MONTANT)
                .setPays(PAYS)
                .setTaux(TAUX)
                .setIbanCompteCrediteur(IBAN)
                .setIbanCompteDebiteur(IBAN2)
                .setCarte(CARTE_OBJ)
                .setSansContact(true);

        // WHEN
        final OperationDTO actual = subject.toDto(obj);

        // THEN
        Assertions.assertEquals(LIBELLE, actual.getLibelle());
        Assertions.assertEquals(CATEGORIE, actual.getCategorie());
        Assertions.assertEquals(DATE_OPERATION, LocalDateTime.parse(actual.getDateOperation()));
        Assertions.assertEquals(ID_OPERATION, UUID.fromString(actual.getIdOperation()));
        Assertions.assertEquals(MONTANT, actual.getMontant());
        Assertions.assertEquals(PAYS, actual.getPays());
        Assertions.assertEquals(TAUX, actual.getTaux());
        Assertions.assertEquals(IBAN, actual.getIbanCompteCrediteur());
        Assertions.assertEquals(IBAN2, actual.getIbanCompteDebiteur());
        Assertions.assertEquals(CARTE_OBJ.getNumeroCarte(), actual.getCarte().getNumeroCarte());
        Assertions.assertTrue(actual.isSansContact());
    }

    @Test
    @DisplayName("Devrait retourner le DTO en obj")
    void toObj() {
        // GIVEN
        final OperationDTO dto = new OperationDTO()
                .setLibelle(LIBELLE)
                .setCategorie(CATEGORIE)
                .setDateOperation(DATE_OPERATION.toString())
                .setIdOperation(ID_OPERATION.toString())
                .setMontant(MONTANT)
                .setPays(PAYS)
                .setTaux(TAUX)
                .setIbanCompteCrediteur(IBAN)
                .setIbanCompteDebiteur(IBAN2)
                .setCarte(CARTE_DTO)
                .setSansContact(true);

        // WHEN
        final Operation actual = subject.toObj(dto);

        // THEN
        Assertions.assertEquals(LIBELLE, actual.getLibelle());
        Assertions.assertEquals(CATEGORIE, actual.getCategorie());
        Assertions.assertEquals(DATE_OPERATION, actual.getDateOperation());
        Assertions.assertEquals(ID_OPERATION, actual.getIdOperation());
        Assertions.assertEquals(MONTANT, actual.getMontant());
        Assertions.assertEquals(PAYS, actual.getPays());
        Assertions.assertEquals(TAUX, actual.getTaux());
        Assertions.assertEquals(IBAN, actual.getIbanCompteCrediteur());
        Assertions.assertEquals(IBAN2, actual.getIbanCompteDebiteur());
        Assertions.assertEquals(CARTE_DTO.getNumeroCarte(), actual.getCarte().getNumeroCarte());
        Assertions.assertTrue(actual.isSansContact());
    }
}
