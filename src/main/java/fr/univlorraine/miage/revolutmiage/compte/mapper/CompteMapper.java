package fr.univlorraine.miage.revolutmiage.compte.mapper;

import fr.univlorraine.miage.revolutmiage.compte.dto.CompteDTO;
import fr.univlorraine.miage.revolutmiage.compte.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utilisateur.mapper.UtilisateurMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {UtilisateurMapper.class}, componentModel = "spring")
public interface CompteMapper {

    @Mapping(target = "utilisateur", source = "entity.utilisateur")
    CompteDTO toDto(Compte entity);

    @Mapping(target = "utilisateur", source = "dto.utilisateur")
    Compte toObj(CompteDTO dto);
}
