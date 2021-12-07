package fr.univlorraine.miage.revolutmiage.utilisateur.mapper;

import fr.univlorraine.miage.revolutmiage.compte.dto.CompteDTO;
import fr.univlorraine.miage.revolutmiage.compte.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utilisateur.dto.UtilisateurDTO;
import fr.univlorraine.miage.revolutmiage.utilisateur.entity.Utilisateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
class UtilisateurMapperImplTest {

    public static final String NOM = "Nom";
    public static final String PRENOM = "Prénom";
    public static final String PAYS = "Pays";
    public static final String NUMERO_PASSEPORT = "93049023578237";
    public static final LocalDate DATE_DE_NAISSANCE = LocalDate.now();
    public static final String NUMERO_TELEPHONE = "0606060606";
    public static final String SECRET = "sd43LKklsd097FSfsdg";
    public static final String IBAN1 = "FR97987893247289";
    public static final String IBAN2 = "FR21574362545654";
    public static final ArrayList<Compte> COMPTES = new ArrayList<>() {{
        add(new Compte().setIban(IBAN1));
        add(new Compte().setIban(IBAN2));
    }};
    public static final ArrayList<CompteDTO> COMPTES_DTO = new ArrayList<>() {{
        add(new CompteDTO().setIban(IBAN1));
        add(new CompteDTO().setIban(IBAN2));
    }};
    @Autowired
    private UtilisateurMapperImpl subject;

    @Test
    @DisplayName("Devrait retourner l'obj en DTO")
    void toDto() {
        // GIVEN
        final Utilisateur obj = new Utilisateur()
                .setNom(NOM)
                .setPrenom(PRENOM)
                .setPays(PAYS)
                .setNumeroPasseport(NUMERO_PASSEPORT)
                .setDateDeNaissance(DATE_DE_NAISSANCE)
                .setNumeroTelephone(NUMERO_TELEPHONE)
                .setSecret(SECRET)
                .setComptes(COMPTES);

        // WHEN
        final UtilisateurDTO actual = subject.toDto(obj);

        // THEN
        Assertions.assertEquals(NOM, actual.getNom());
        Assertions.assertEquals(PRENOM, actual.getPrenom());
        Assertions.assertEquals(PAYS, actual.getPays());
        Assertions.assertEquals(NUMERO_PASSEPORT, actual.getNumeroPasseport());
        Assertions.assertEquals(DATE_DE_NAISSANCE, LocalDate.parse(actual.getDateDeNaissance()));
        Assertions.assertEquals(NUMERO_TELEPHONE, actual.getNumeroTelephone());
        Assertions.assertEquals(SECRET, actual.getSecret());
        Assertions.assertEquals(COMPTES.size(), actual.getComptes().size());
    }

    @Test
    @DisplayName("Devrait retourner le DTO en obj")
    void toObj() {
        // GIVEN
        final UtilisateurDTO dto = new UtilisateurDTO()
                .setNom(NOM)
                .setPrenom(PRENOM)
                .setPays(PAYS)
                .setNumeroPasseport(NUMERO_PASSEPORT)
                .setDateDeNaissance(DATE_DE_NAISSANCE.toString())
                .setNumeroTelephone(NUMERO_TELEPHONE)
                .setSecret(SECRET)
                .setComptes(COMPTES_DTO);

        // WHEN
        final Utilisateur actual = subject.toObj(dto);

        // THEN
        Assertions.assertEquals(NOM, actual.getNom());
        Assertions.assertEquals(PRENOM, actual.getPrenom());
        Assertions.assertEquals(PAYS, actual.getPays());
        Assertions.assertEquals(NUMERO_PASSEPORT, actual.getNumeroPasseport());
        Assertions.assertEquals(DATE_DE_NAISSANCE, actual.getDateDeNaissance());
        Assertions.assertEquals(NUMERO_TELEPHONE, actual.getNumeroTelephone());
        Assertions.assertEquals(SECRET, actual.getSecret());
        Assertions.assertEquals(COMPTES_DTO.size(), actual.getComptes().size());
    }
}
