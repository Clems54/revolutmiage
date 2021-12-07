package fr.univlorraine.miage.revolutmiage.compte.mapper;

import fr.univlorraine.miage.revolutmiage.compte.dto.CompteDTO;
import fr.univlorraine.miage.revolutmiage.compte.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utilisateur.mapper.UtilisateurMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {UtilisateurMapper.class}, componentModel = "spring")
public interface CompteMapper {

    CompteDTO toDto(Compte entity);

    Compte toObj(CompteDTO dto);
}
