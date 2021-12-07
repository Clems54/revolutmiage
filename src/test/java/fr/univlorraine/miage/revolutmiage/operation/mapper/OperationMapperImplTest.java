package fr.univlorraine.miage.revolutmiage.operation.mapper;

import fr.univlorraine.miage.revolutmiage.compte.dto.CompteDTO;
import fr.univlorraine.miage.revolutmiage.compte.entity.Compte;
import fr.univlorraine.miage.revolutmiage.operation.dto.OperationDTO;
import fr.univlorraine.miage.revolutmiage.operation.entity.Operation;
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
    public static final String LIBELLE = "Libelle";
    public static final String CATEGORIE = "Categorie";
    public static final LocalDateTime DATE_OPERATION = LocalDateTime.now();
    public static final UUID ID_OPERATION = UUID.randomUUID();
    public static final double MONTANT = 10.5;
    public static final String PAYS = "Pays";
    public static final int TAUX = 10;

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
                .setCompteCrediteur(new Compte().setIban(IBAN));

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
        Assertions.assertEquals(IBAN, actual.getCompteCrediteur().getIban());
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
                .setCompteCrediteur(new CompteDTO().setIban(IBAN));

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
        Assertions.assertEquals(IBAN, actual.getCompteCrediteur().getIban());
    }
}
