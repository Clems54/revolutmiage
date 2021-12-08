package fr.univlorraine.miage.revolutmiage.utilisateur.infra.mapper;

import fr.univlorraine.miage.revolutmiage.utilisateur.infra.dto.UtilisateurDTO;
import fr.univlorraine.miage.revolutmiage.utilisateur.domain.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = LocalDate.class)
public interface UtilisateurMapper {

    @Mapping(target = "dateDeNaissance", expression = "java(entity.getDateDeNaissance().toString())")
    UtilisateurDTO toDto(Utilisateur entity);

    @Mapping(target = "dateDeNaissance", expression = "java(LocalDate.parse(dto.getDateDeNaissance()))")
    Utilisateur toObj(UtilisateurDTO dto);
}
