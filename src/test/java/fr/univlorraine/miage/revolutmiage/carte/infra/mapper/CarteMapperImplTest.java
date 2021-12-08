package fr.univlorraine.miage.revolutmiage.carte.infra.mapper;

import fr.univlorraine.miage.revolutmiage.carte.infra.dto.CarteDTO;
import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.compte.infra.dto.CompteDTO;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
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

    @Autowired
    private CarteMapperImpl subject;

    @Test
    @DisplayName("Devrait retourner l'obj en DTO")
    void toDto() {
        // GIVEN
        final Carte obj = new Carte()
                .setBloquee(true)
                .setLocalisation(true)
                .setPlafond(true)
                .setSansContact(true)
                .setVirtuelle(true)
                .setCode(CODE)
                .setCryptogramme(CRYPTOGRAMME)
                .setNumeroCarte(NUMERO_CARTE)
                .setCompte(new Compte().setIban(IBAN));

        // WHEN
        final CarteDTO actual = subject.toDto(obj);

        // THEN
        Assertions.assertTrue(actual.isBloquee());
        Assertions.assertTrue(actual.isLocalisation());
        Assertions.assertTrue(actual.isPlafond());
        Assertions.assertTrue(actual.isSansContact());
        Assertions.assertTrue(actual.isVirtuelle());
        Assertions.assertEquals(CODE, actual.getCode());
        Assertions.assertEquals(CRYPTOGRAMME, actual.getCryptogramme());
        Assertions.assertEquals(NUMERO_CARTE, actual.getNumeroCarte());
        Assertions.assertEquals(IBAN, actual.getCompte().getIban());
    }

    @Test
    @DisplayName("Devrait retourner le DTO en obj")
    void toObj() {
        // GIVEN
        final CarteDTO dto = new CarteDTO()
                .setBloquee(true)
                .setLocalisation(true)
                .setPlafond(true)
                .setSansContact(true)
                .setVirtuelle(true)
                .setCode(CODE)
                .setCryptogramme(CRYPTOGRAMME)
                .setNumeroCarte(NUMERO_CARTE)
                .setCompte(new CompteDTO().setIban(IBAN));

        // WHEN
        final Carte actual = subject.toObj(dto);

        // THEN
        Assertions.assertTrue(actual.isBloquee());
        Assertions.assertTrue(actual.isLocalisation());
        Assertions.assertTrue(actual.isPlafond());
        Assertions.assertTrue(actual.isSansContact());
        Assertions.assertTrue(actual.isVirtuelle());
        Assertions.assertEquals(CODE, actual.getCode());
        Assertions.assertEquals(CRYPTOGRAMME, actual.getCryptogramme());
        Assertions.assertEquals(NUMERO_CARTE, actual.getNumeroCarte());
        Assertions.assertEquals(IBAN, actual.getCompte().getIban());
    }
}
