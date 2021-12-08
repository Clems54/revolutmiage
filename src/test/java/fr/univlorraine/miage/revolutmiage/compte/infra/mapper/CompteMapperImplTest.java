package fr.univlorraine.miage.revolutmiage.compte.infra.mapper;

import fr.univlorraine.miage.revolutmiage.compte.infra.dto.CompteDTO;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utilisateur.infra.dto.UtilisateurDTO;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CompteMapperImplTest {

    public static final String IBAN = "FR761034328909432347";
    public static final String NUMERO_PASSEPORT = "9084375897235";

    @Autowired
    private CompteMapperImpl subject;

    @Test
    @DisplayName("Devrait retourner l'obj en DTO")
    void toDto() {
        // GIVEN
        final Compte obj = new Compte()
                .setIban(IBAN)
                .setUtilisateur(new Utilisateur().setNumeroPasseport(NUMERO_PASSEPORT).setDateDeNaissance(LocalDate.now()));

        // WHEN
        final CompteDTO actual = subject.toDto(obj);

        // THEN
        Assertions.assertEquals(IBAN, actual.getIban());
        Assertions.assertEquals(NUMERO_PASSEPORT, actual.getUtilisateur().getNumeroPasseport());
    }

    @Test
    @DisplayName("Devrait retourner le DTO en obj")
    void toObj() {
        // GIVEN
        final CompteDTO dto = new CompteDTO()
                .setIban(IBAN)
                .setUtilisateur(new UtilisateurDTO().setNumeroPasseport(NUMERO_PASSEPORT).setDateDeNaissance(LocalDate.now().toString()));

        // WHEN
        final Compte actual = subject.toObj(dto);

        // THEN
        Assertions.assertEquals(IBAN, actual.getIban());
        Assertions.assertEquals(NUMERO_PASSEPORT, actual.getUtilisateur().getNumeroPasseport());
    }
}
