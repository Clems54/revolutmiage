package fr.univlorraine.miage.revolutmiage.utilisateur.mapper;

import fr.univlorraine.miage.revolutmiage.utilisateur.entity.Utilisateur;
import fr.univlorraine.miage.revolutmiage.utilisateur.dto.UtilisateurDTO;
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
