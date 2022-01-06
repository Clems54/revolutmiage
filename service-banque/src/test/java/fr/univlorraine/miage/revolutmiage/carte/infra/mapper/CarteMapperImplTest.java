package fr.univlorraine.miage.revolutmiage.carte.infra.mapper;

import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.carte.infra.dto.CarteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarteMapperImplTest {
    public static final String CODE = "0000";
    public static final String CRYPTOGRAMME = "666";
    public static final String NUMERO_CARTE = "5000657823458954";
    public static final String IBAN = "FR761034328909432347";
    public static final int PLAFOND = 2000;

    @Autowired
    private CarteMapperImpl subject;

    @Test
    @DisplayName("Devrait retourner l'obj en DTO")
    void toDto() {
        // GIVEN
        final Carte obj = new Carte()
                .setBloquee(true)
                .setLocalisation(true)
                .setPlafond(PLAFOND)
                .setSansContact(true)
                .setVirtuelle(true)
                .setCode(CODE)
                .setCryptogramme(CRYPTOGRAMME)
                .setNumeroCarte(NUMERO_CARTE)
                .setCompteIban(IBAN);

        // WHEN
        final CarteDTO actual = subject.toDto(obj);

        // THEN
        Assertions.assertTrue(actual.isBloquee());
        Assertions.assertTrue(actual.isLocalisation());
        Assertions.assertTrue(actual.isSansContact());
        Assertions.assertTrue(actual.isVirtuelle());
        Assertions.assertEquals(PLAFOND, actual.getPlafond());
        Assertions.assertEquals(CODE, actual.getCode());
        Assertions.assertEquals(CRYPTOGRAMME, actual.getCryptogramme());
        Assertions.assertEquals(NUMERO_CARTE, actual.getNumeroCarte());
        Assertions.assertEquals(IBAN, actual.getCompteIban());
    }

    @Test
    @DisplayName("Devrait retourner le DTO en obj")
    void toObj() {
        // GIVEN
        final CarteDTO dto = new CarteDTO()
                .setBloquee(true)
                .setLocalisation(true)
                .setPlafond(PLAFOND)
                .setSansContact(true)
                .setVirtuelle(true)
                .setCode(CODE)
                .setCryptogramme(CRYPTOGRAMME)
                .setNumeroCarte(NUMERO_CARTE)
                .setCompteIban(IBAN);

        // WHEN
        final Carte actual = subject.toObj(dto);

        // THEN
        Assertions.assertTrue(actual.isBloquee());
        Assertions.assertTrue(actual.isLocalisation());
        Assertions.assertTrue(actual.isSansContact());
        Assertions.assertTrue(actual.isVirtuelle());
        Assertions.assertEquals(PLAFOND, actual.getPlafond());
        Assertions.assertEquals(CODE, actual.getCode());
        Assertions.assertEquals(CRYPTOGRAMME, actual.getCryptogramme());
        Assertions.assertEquals(NUMERO_CARTE, actual.getNumeroCarte());
        Assertions.assertEquals(IBAN, actual.getCompteIban());
    }
}
